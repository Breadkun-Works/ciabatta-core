package com.ciabatta.core.cafe.domain.model.enums

sealed interface CafeEnums {
    interface Menu : CafeEnums {
        enum class Category(
            val label: String,
        ) : Menu {
            COFFEE("커피"),
            TEA("티"),
            DRINK("음료"),
            SEASON("시즌"),
        }

        enum class Temperature(
            val label: String,
        ) : Menu {
            HOT("핫"),
            ICED("아이스"),
        }
    }

    interface Cart : CafeEnums {
        enum class Status(
            val label: String,
        ) : Cart {
            ACTIVE("활성"),
            INACTIVE("비활성"),
        }
    }
}
