package com.breadkun.backend.cafe.menu.dto.response

import com.breadkun.backend.cafe.menu.model.CafeMenu
import com.breadkun.backend.global.common.enums.Location
import com.breadkun.backend.domain.cafe.menu.model.enums.CafeMenuCategory
import com.breadkun.backend.domain.cafe.menu.model.enums.DrinkTemperature
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class CafeMenuDTO(
    @Schema(description = "메뉴의 고유 ID")
    val id: String,

    @Schema(description = "카페의 위치")
    val cafeLocation: Location,

    @Schema(description = "메뉴의 이름")
    val name: String,

    @Schema(description = "메뉴 가격")
    val price: Int,

    @Schema(description = "메뉴의 카테고리")
    val category: CafeMenuCategory,

    @Schema(description = "음료의 온도")
    val drinkTemperature: DrinkTemperature,

    @Schema(description = "판매 가능 여부")
    val available: Boolean,

    @Schema(description = "메뉴에 대한 설명")
    val description: String?,

    @Schema(description = "이미지 파일 이름")
    val imageFilename: String?,

    @Schema(description = "이미지의 URL")
    val imageUrl: String?,

    @Schema(description = "생성일")
    val createdAt: LocalDateTime,

    @Schema(description = "생성자 ID")
    val createdById: String,

    @Schema(description = "수정자 ID")
    val updatedAt: LocalDateTime?,

    @Schema(description = "수정일")
    val updatedById: String?
) {
    companion object {
        fun fromModel(cafeMenu: com.breadkun.backend.cafe.menu.model.CafeMenu): com.breadkun.backend.cafe.menu.dto.response.CafeMenuDTO {
            return com.breadkun.backend.cafe.menu.dto.response.CafeMenuDTO(
                id = cafeMenu.id,
                cafeLocation = cafeMenu.cafeLocation,
                name = cafeMenu.name,
                price = cafeMenu.price,
                category = cafeMenu.category,
                drinkTemperature = cafeMenu.drinkTemperature,
                available = cafeMenu.available,
                description = cafeMenu.description,
                imageFilename = cafeMenu.imageFilename,
                imageUrl = cafeMenu.imageUrl,
                createdAt = cafeMenu.createdAt,
                createdById = cafeMenu.createdById,
                updatedAt = cafeMenu.updatedAt,
                updatedById = cafeMenu.updatedById
            )
        }
    }
}