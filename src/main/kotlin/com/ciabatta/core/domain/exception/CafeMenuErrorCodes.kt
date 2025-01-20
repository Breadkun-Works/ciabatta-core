package com.ciabatta.core.domain.exception

enum class CafeMenuErrorCodes(val code: String, val message: String) {
    MENU_NOT_FOUND("CAFE_MENU_NOT_FOUND", "The requested cafe menu was not found"),
    INVALID_MENU_CATEGORY("INVALID_MENU_CATEGORY", "The provided menu category is invalid")
}