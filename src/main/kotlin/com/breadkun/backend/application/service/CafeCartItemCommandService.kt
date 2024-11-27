package com.breadkun.backend.application.service

import com.breadkun.backend.application.dto.CafeCartItemCreateDTO
import com.breadkun.backend.application.port.input.CafeCartItemCommandUseCase
import com.breadkun.backend.domain.model.CafeCartItem
import com.breadkun.backend.application.port.input.CafeCartQueryUseCase
import com.breadkun.backend.application.port.input.CafeMenuQueryUseCase
import com.breadkun.backend.application.port.output.CafeCartItemCommandPort
import com.breadkun.backend.domain.model.CafeCart
import com.breadkun.backend.domain.model.enums.CafeEnums
import com.breadkun.backend.global.common.dto.DeleteIdsDTO
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
        val cafeCart = validateCart(cartId)

        return dtos.map { dto ->
            validateMenuAndLocation(cafeCart, dto)

            val entity = CafeCartItem.fromCreateDTO(cartId, userUUID, userName, dto).toEntity()
            val savedEntity = cafeCartItemCommandPort.save(entity)
            CafeCartItem.fromEntity(savedEntity)
        }
    }

    override suspend fun deleteCafeCartItems(
        dto: DeleteIdsDTO
    ) {
        return cafeCartItemCommandPort.deleteAll(dto.ids)
    }

    override suspend fun deleteCafeCartItemsByCafeCartId(
        cafeCartId: String
    ) {
        return cafeCartItemCommandPort.deleteAllByCafeCartId(cafeCartId)
    }

    private suspend fun validateCart(cartId: String): CafeCart {
        return cafeCartQueryUseCase.findCafeCartById(cartId)?.also { cafeCart ->
            if (cafeCart.status != CafeEnums.Cart.Status.ACTIVE) {
                throw ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "CafeCart must be ACTIVE")
            }
        } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "CafeCart not found with id: $cartId")
    }


    private suspend fun validateMenuAndLocation(
        cafeCart: CafeCart,
        dto: CafeCartItemCreateDTO
    ) {
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