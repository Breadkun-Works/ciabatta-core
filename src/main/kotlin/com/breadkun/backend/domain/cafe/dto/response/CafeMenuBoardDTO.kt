package com.breadkun.backend.domain.cafe.dto.response

import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import com.breadkun.backend.domain.cafe.model.enum.CafeMenuCategory
import com.breadkun.backend.domain.cafe.model.enum.DrinkTemperature
import io.swagger.v3.oas.annotations.media.Schema

data class CafeMenuBoardDTO(
    @Schema(description = "카페의 위치")
    val cafeLocation: CafeLocation,

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

    @Schema(description = "메뉴에 대한 설명")
    val description: String?,

    @Schema(description = "이미지 파일 이름")
    val imageFilename: String?,

    @Schema(description = "이미지의 URL")
    val imageUrl: String?
)