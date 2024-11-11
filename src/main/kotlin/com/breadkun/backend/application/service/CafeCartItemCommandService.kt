package com.breadkun.backend.application.service

import com.breadkun.backend.domain.model.CafeCartItem
import com.breadkun.backend.application.port.input.CafeCartQueryUseCase
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class CafeCartItemCommandService(
    private val cafeCartItemCommandPort: com.breadkun.backend.application.port.output.CafeCartItemCommandPort,
    private val cafeCartQueryUseCase: CafeCartQueryUseCase,
    private val cafeMenuQueryUseCase: com.breadkun.backend.application.port.input.CafeMenuQueryUseCase
) : com.breadkun.backend.application.port.input.CafeCartItemCommandUseCase {
    @Transactional
    override suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        dtos: List<com.breadkun.backend.application.dto.CafeCartItemCreateDTO>
    ): List<CafeCartItem> {
        validateCartAndMenuExistenceAndLocationMatch(cartId, dtos)

        return cafeCartItemCommandPort.saveAll(dtos.map { CafeCartItem.fromCreateDTO(cartId, userUUID, it).toEntity() })
            .map {
                CafeCartItem.fromEntity(it)
            }
    }

    private suspend fun validateCartAndMenuExistenceAndLocationMatch(
        cartId: String,
        dtos: List<com.breadkun.backend.application.dto.CafeCartItemCreateDTO>
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