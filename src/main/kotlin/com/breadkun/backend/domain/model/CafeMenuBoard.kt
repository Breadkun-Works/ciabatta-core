package com.breadkun.backend.domain.model

import com.breadkun.backend.domain.model.enums.CafeEnums
import com.breadkun.backend.global.common.enums.GlobalEnums

data class CafeMenuBoard(
    val cafeLocation: GlobalEnums.Location,

    val name: String,

    val category: CafeEnums.Menu.Category,

    val options: List<CafeMenuBoardOptionDTO>
)

data class CafeMenuBoardOptionDTO(
    val drinkTemperature: CafeEnums.Menu.Temperature,

    val id: String,

    val available: Boolean,

    val price: Int,

    val deposit: Int,

    val description: String?,

    val imageFilename: String?,

    val imageUrl: String?
)