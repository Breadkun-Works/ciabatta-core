package com.breadkun.backend.domain.cafe.dto.response

import com.breadkun.backend.domain.cafe.model.CafeMenu
import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import com.breadkun.backend.domain.cafe.model.enum.CafeMenuCategory
import com.breadkun.backend.domain.cafe.model.enum.DrinkTemperature
import io.swagger.v3.oas.annotations.media.Schema

data class CafeMenuOptionDTO(
    @Schema(description = "음료의 온도", example = "HOT")
    val drinkTemperature: DrinkTemperature,

    @Schema(description = "메뉴의 고유 ID")
    val id: String,

    @Schema(description = "판매 가능 여부", example = "true")
    val available: Boolean,

    @Schema(description = "메뉴 가격", example = "4500")
    val price: Int,

    @Schema(description = "메뉴에 대한 설명", example = "신선한 원두로 만든 아메리카노")
    val description: String?,

    @Schema(description = "이미지 파일 이름", example = "americano.png")
    val imageFilename: String?,

    @Schema(description = "이미지의 URL", example = "http://example.com/americano.png")
    val imageUrl: String?
)

data class CafeMenuBoardResponseDTO(
    @Schema(description = "카페의 위치", example = "KANGCHON")
    val cafeLocation: CafeLocation,

    @Schema(description = "메뉴의 이름", example = "아메리카노")
    val name: String,

    @Schema(description = "메뉴의 카테고리", example = "COFFEE")
    val category: CafeMenuCategory,

    @Schema(description = "메뉴의 옵션 리스트")
    val options: List<CafeMenuOptionDTO>
) {
    companion object {
        fun fromModel(cafeMenus: List<CafeMenu>): List<CafeMenuBoardResponseDTO> {
            return cafeMenus
                .groupBy { cafeMenu ->
                    GroupingKey(
                        cafeMenu.cafeLocation,
                        cafeMenu.name,
                        cafeMenu.category
                    )
                }
                .map { (key, groupedMenus) ->
                    CafeMenuBoardResponseDTO(
                        cafeLocation = key.cafeLocation,
                        name = key.name,
                        category = key.category,
                        options = groupedMenus.map { menu ->
                            CafeMenuOptionDTO(
                                id = menu.id,
                                drinkTemperature = menu.drinkTemperature,
                                available = menu.available,
                                price = menu.price,
                                description = menu.description,
                                imageFilename = menu.imageFilename,
                                imageUrl = menu.imageUrl
                            )
                        }
                    )
                }
        }
    }
}

data class GroupingKey(
    val cafeLocation: CafeLocation,
    val name: String,
    val category: CafeMenuCategory
)