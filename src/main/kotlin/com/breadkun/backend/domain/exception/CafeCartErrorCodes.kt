package com.breadkun.backend.domain.exception

enum class CafeCartErrorCodes(val code: String, val message: String) {
    CART_NOT_FOUND("CAFE_CART_NOT_FOUND", "The requested cafe cart was not found"),
    CART_EXPIRED("CAFE_CART_EXPIRED", "The cafe cart has expired")
}