package com.breadkun.backend.cafe.application.service

import com.breadkun.backend.cafe.application.dto.request.CafeCartItemCreateDTO
import com.breadkun.backend.cafe.domain.model.CafeCartItem
import com.breadkun.backend.cafe.application.port.input.CafeCartItemCommandUseCase
import com.breadkun.backend.cafe.application.port.input.CafeCartQueryUseCase
import com.breadkun.backend.cafe.application.port.input.CafeMenuQueryUseCase
import com.breadkun.backend.cafe.application.port.output.CafeCartItemCommandPort
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
        dtos: List<CafeCartItemCreateDTO>
    ): List<CafeCartItem> {
        validateCartAndMenuExistenceAndLocationMatch(cartId, dtos)

        return cafeCartItemCommandPort.saveAll(dtos.map { it.toModel(cartId, userUUID) })
            .map {
                CafeCartItem.fromModel(it)
            }
    }

    private suspend fun validateCartAndMenuExistenceAndLocationMatch(
        cartId: String,
        dtos: List<CafeCartItemCreateDTO>
    ) {
        val cafeCart = cafeCartQueryUseCase.findActiveCafeCartById(cartId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "CafeCart not found with id: $cartId")

        dtos.forEach { dto ->
            val cafeMenu = cafeMenuQueryUseCase.findCafeMenuById(dto.cafeMenuId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "CafeMenu not found with id: ${dto.cafeMenuId}")

            require(cafeCart.cafeLocation == cafeMenu.cafeLocation) {
                "CafeCart location and CafeMenu location do not match. " +
                        "Cart location: ${cafeCart.cafeLocation}, " +
                        "Menu location: ${cafeMenu.cafeLocation}"
            }
        }
    }
}