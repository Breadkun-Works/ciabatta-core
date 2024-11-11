package com.breadkun.backend.domain.exception

enum class CafeCartItemErrorCodes(val code: String, val message: String) {
    ITEM_NOT_FOUND("CAFE_CART_ITEM_NOT_FOUND", "The requested cafe cart item was not found"),
    INVALID_ITEM_QUANTITY("INVALID_ITEM_QUANTITY", "The item quantity is invalid")
}