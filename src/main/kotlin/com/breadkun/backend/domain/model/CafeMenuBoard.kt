package com.breadkun.backend.domain.model

import com.breadkun.backend.global.common.enums.Location
import com.breadkun.backend.domain.model.enums.CafeMenuCategory
import com.breadkun.backend.domain.model.enums.DrinkTemperature
import io.swagger.v3.oas.annotations.media.Schema

data class CafeMenuBoard(
    @Schema(description = "카페의 위치")
    val cafeLocation: Location,

    @Schema(description = "메뉴의 이름")
    val name: String,

    @Schema(description = "메뉴의 카테고리")
    val category: CafeMenuCategory,

    @Schema(description = "메뉴의 옵션 리스트")
    val options: List<CafeMenuBoardOptionDTO>
)

data class CafeMenuBoardOptionDTO(
    @Schema(description = "음료의 온도")
    val drinkTemperature: DrinkTemperature,

    @Schema(description = "메뉴의 고유 ID")
    val id: String,

    @Schema(description = "판매 가능 여부")
    val available: Boolean,

    @Schema(description = "메뉴 가격")
    val price: Int,

    @Schema(description = "컵 보증금")
    val deposit: Int,

    @Schema(description = "메뉴에 대한 설명")
    val description: String?,

    @Schema(description = "이미지 파일 이름")
    val imageFilename: String?,

    @Schema(description = "이미지의 URL")
    val imageUrl: String?
)