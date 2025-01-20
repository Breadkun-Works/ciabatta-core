package com.ciabatta.core.domain.model

import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.common.enums.GlobalEnums

data class CafeMenuBoard(
    val cafeLocation: GlobalEnums.Location,

    val name: String,

    val category: CafeEnums.Menu.Category,

    val options: List<CafeMenuBoardOptionDTO>
)

data class CafeMenuBoardOptionDTO(
    val drinkTemperature: CafeEnums.Menu.Temperature,

    val id: Long,

    val available: Boolean,

    val price: Int,

    val deposit: Int,

    val description: String?,

    val imageFilename: String?,

    val imageUrl: String?
)