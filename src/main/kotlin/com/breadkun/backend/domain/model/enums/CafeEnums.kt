package com.breadkun.backend.domain.model.enums

sealed interface CafeEnums {
    interface Menu : CafeEnums {
        enum class Category(
            val label: String
        ) : Menu {
            COFFEE("커피"),
            TEA("티"),
            DRINK("음료")
        }

        enum class Temperature(
            val label: String
        ) : Menu {
            HOT("핫"),
            ICED("아이스")
        }
    }
}