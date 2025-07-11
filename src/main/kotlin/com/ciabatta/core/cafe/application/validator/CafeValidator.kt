package com.ciabatta.core.cafe.application.validator

import com.ciabatta.core.cafe.domain.model.CafeCart
import com.ciabatta.core.cafe.domain.model.CafeCartItem
import com.ciabatta.core.cafe.domain.model.CafeMenu
import com.ciabatta.core.cafe.domain.model.enums.CafeEnums
import com.ciabatta.core.global.exception.BusinessException
import com.ciabatta.core.global.exception.ErrorCode
import org.springframework.stereotype.Component

@Component
class CafeValidator() {
    suspend fun assertCartIsActive(cafeCart: CafeCart) {
        if (cafeCart.status != CafeEnums.Cart.Status.ACTIVE) {
            throw BusinessException(ErrorCode.CA_2002, "CafeCart must be ACTIVE")
        }
    }

    suspend fun assertCartAndMenuLocationMatch(
        cafeCart: CafeCart,
        cafeMenu: CafeMenu,
    ) {
        if (cafeCart.cafeLocation != cafeMenu.cafeLocation) {
            throw BusinessException(
                ErrorCode.CA_2003,
                "CafeCart location and CafeMenu location do not match. " +
                    "Cart location: ${cafeCart.cafeLocation}, " +
                    "Menu location: ${cafeMenu.cafeLocation}",
            )
        }
    }

    suspend fun assertCartOwnership(
        userUUID: String,
        cafeCart: CafeCart,
    ) {
        if (cafeCart.createdById != userUUID) {
            throw BusinessException(ErrorCode.CA_2004, "CafeCart creator ID does not match the provided UUID.")
        }
    }

    suspend fun assertCartItemOwnership(
        userUUID: String,
        cafeCartItem: CafeCartItem,
    ) {
        if (cafeCartItem.createdById != userUUID) {
            throw BusinessException(ErrorCode.CA_3002, "CafeCartItem creator ID does not match the provided UUID.")
        }
    }
}
