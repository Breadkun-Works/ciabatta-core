package com.ciabatta.core.application.validator

import com.ciabatta.core.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.application.port.input.CafeMenuQueryUseCase
import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.exception.BusinessException
import com.ciabatta.core.global.exception.ErrorCode
import org.springframework.stereotype.Component

@Component
class CafeCartValidator(
    private val cafeCartQueryUseCase: CafeCartQueryUseCase,
    private val cafeMenuQueryUseCase: CafeMenuQueryUseCase
) {
    suspend fun validateCart(cartId: String): CafeCart =
        cafeCartQueryUseCase.findCafeCartById(cartId).also { cafeCart ->
            if (cafeCart.status != CafeEnums.Cart.Status.ACTIVE) {
                throw BusinessException(ErrorCode.CA_2002, "CafeCart must be ACTIVE")
            }
        }

    suspend fun validateMenuAndLocation(
        cafeCart: CafeCart,
        dto: CafeCartItemCreateDTO
    ) {
        val cafeMenu = cafeMenuQueryUseCase.findCafeMenuById(dto.cafeMenuId)

        if (cafeCart.cafeLocation != cafeMenu.cafeLocation) {
            throw BusinessException(
                ErrorCode.CA_2003,
                "CafeCart location and CafeMenu location do not match. " +
                        "Cart location: ${cafeCart.cafeLocation}, " +
                        "Menu location: ${cafeMenu.cafeLocation}"
            )
        }
    }
}