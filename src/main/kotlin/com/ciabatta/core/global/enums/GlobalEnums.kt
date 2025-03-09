package com.ciabatta.core.global.enums

interface GlobalEnums {
    enum class Location(
        val label: String
    ) : GlobalEnums {
        KANGCHON("강촌"),
        EULJI("을지")
    }

    enum class IncludeOption(
        val label: String
    ) : GlobalEnums {
        DETAILS("상세(참조) 데이터 포함")
    }
}