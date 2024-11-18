package com.breadkun.backend.application.service

import com.breadkun.backend.application.dto.CafeCartItemCreateDTO
import com.breadkun.backend.application.port.input.CafeCartItemCommandUseCase
import com.breadkun.backend.domain.model.CafeCartItem
import com.breadkun.backend.application.port.input.CafeCartQueryUseCase
import com.breadkun.backend.application.port.input.CafeMenuQueryUseCase
import com.breadkun.backend.application.port.output.CafeCartItemCommandPort
import com.breadkun.backend.domain.model.enums.CafeEnums
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class CafeCartItemCommandService(
    private val cafeCartItemCommandPort: CafeCartItemCommandPort,
    private val cafeCartQueryUseCase: CafeCartQueryUseCase,
    private val cafeMenuQueryUseCase: CafeMenuQueryUseCase
) : CafeCartItemCommandUseCase {
    @Transactional
    override suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        userName: String,
        dtos: List<CafeCartItemCreateDTO>
    ): List<CafeCartItem> {
        validateCartAndMenuExistenceAndLocationMatch(cartId, dtos)

        return cafeCartItemCommandPort.saveAll(dtos.map {
            CafeCartItem.fromCreateDTO(cartId, userUUID, userName, it).toEntity()
        })
            .map {
                CafeCartItem.fromEntity(it)
            }
    }

    private suspend fun validateCartAndMenuExistenceAndLocationMatch(
        cartId: String,
        dtos: List<CafeCartItemCreateDTO>
    ) {
        val cafeCart = cafeCartQueryUseCase.findCafeCartById(cartId, null)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "CafeCart not found with id: $cartId")

        if (cafeCart.status != CafeEnums.Cart.Status.ACTIVE) {
            throw ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "CafeCart must be ACTIVE")
        }

        for (dto in dtos) {
            val cafeMenu = cafeMenuQueryUseCase.findCafeMenuById(dto.cafeMenuId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "CafeMenu not found with id: ${dto.cafeMenuId}")

            if (cafeCart.cafeLocation != cafeMenu.cafeLocation) {
                throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "CafeCart location and CafeMenu location do not match. " +
                            "Cart location: ${cafeCart.cafeLocation}, " +
                            "Menu location: ${cafeMenu.cafeLocation}"
                )
            }
        }
    }
}