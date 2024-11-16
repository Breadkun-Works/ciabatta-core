package com.breadkun.backend.global.common.enums

interface GlobalEnums {
    enum class Location(
        val label: String
    ) : GlobalEnums {
        KANGCHON("강촌"),
        EULJI("을지")
    }
}