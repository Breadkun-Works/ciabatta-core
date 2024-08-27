package com.breadkun.backend.domain.cafe.model.enum

import com.fasterxml.jackson.annotation.JsonValue

enum class CafeMenuCategory(@JsonValue val displayName: String) {
    COFFEE("커피"),
    TEA("티"),
    DRINK("음료");

    override fun toString(): String {
        return displayName
    }
}