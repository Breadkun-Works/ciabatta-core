package com.ciabatta.core.application.validator

import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.domain.model.CafeMenu
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.exception.BusinessException
import com.ciabatta.core.global.exception.ErrorCode
import org.springframework.stereotype.Component

@Component
class CafeCartValidator() {
    suspend fun assertCartIsActive(
        cafeCart: CafeCart
    ): Unit {
        if (cafeCart.status != CafeEnums.Cart.Status.ACTIVE) {
            throw BusinessException(ErrorCode.CA_2002, "CafeCart must be ACTIVE")
        }
    }

    suspend fun assertCartAndMenuLocationMatch(
        cafeCart: CafeCart,
        cafeMenu: CafeMenu
    ): Unit {
        if (cafeCart.cafeLocation != cafeMenu.cafeLocation) {
            throw BusinessException(
                ErrorCode.CA_2003,
                "CafeCart location and CafeMenu location do not match. " +
                        "Cart location: ${cafeCart.cafeLocation}, " +
                        "Menu location: ${cafeMenu.cafeLocation}"
            )
        }
    }

    suspend fun assertCartOwnership(
        cafeCart: CafeCart,
        userUUID: String
    ): Unit {
        if (cafeCart.createdById != userUUID) {
            throw BusinessException(ErrorCode.CA_2004, "CafeCart creator ID does not match the provided UUID.")
        }
    }
}