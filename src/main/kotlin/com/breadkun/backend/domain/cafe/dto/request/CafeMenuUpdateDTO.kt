package com.breadkun.backend.domain.cafe.dto.request

import com.breadkun.backend.domain.cafe.dto.response.CafeMenuDTO
import com.breadkun.backend.domain.cafe.model.CafeMenu
import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import com.breadkun.backend.domain.cafe.model.enum.CafeMenuCategory
import com.breadkun.backend.domain.cafe.model.enum.DrinkTemperature
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class CafeMenuUpdateDTO(
    @Schema(description = "카페의 위치", example = "KANGCHON")
    val cafeLocation: CafeLocation? = null,

    @field:Size(max = 70, message = "메뉴 이름은 70자 이내여야 합니다.")
    @Schema(description = "메뉴의 이름", example = "아메리카노")
    val name: String? = null,

    @field:Positive(message = "메뉴 가격은 양수여야 합니다.")
    @Schema(description = "메뉴의 가격", example = "4500")
    val price: Int? = null,

    @Schema(description = "메뉴의 카테고리", example = "COFFEE")
    val category: CafeMenuCategory? = null,

    @Schema(description = "음료의 온도", example = "HOT")
    val drinkTemperature: DrinkTemperature? = null,

    @Schema(description = "판매 가능 여부", example = "true")
    val available: Boolean? = null,

    @field:Size(max = 255, message = "메뉴 설명은 255자 이내여야 합니다.")
    @Schema(description = "메뉴에 대한 설명", example = "신선한 원두로 만든 아메리카노")
    val description: String? = null,

    @field:Size(max = 100, message = "이미지 파일 이름은 100자 이내여야 합니다.")
    @Schema(description = "이미지 파일 이름", example = "americano.png")
    val imageFilename: String? = null,

    @field:Size(max = 255, message = "이미지 URL은 255자 이내여야 합니다.")
    @Schema(description = "이미지의 URL", example = "http://example.com/americano.png")
    val imageUrl: String? = null,

    @field:NotBlank(message = "수정자 ID는 필수입니다.")
    @Schema(description = "수정자 ID")
    val updatedById: String,
) {
    fun toModel(cafeMenuId: String, existingMenu: CafeMenuDTO): CafeMenu {
        return CafeMenu(
            id = cafeMenuId,
            cafeLocation = cafeLocation ?: existingMenu.cafeLocation,
            name = name ?: existingMenu.name,
            price = price ?: existingMenu.price,
            category = category ?: existingMenu.category,
            drinkTemperature = drinkTemperature ?: existingMenu.drinkTemperature,
            available = available ?: existingMenu.available,
            description = description ?: existingMenu.description,
            imageFilename = imageFilename ?: existingMenu.imageFilename,
            imageUrl = imageUrl ?: existingMenu.imageUrl,
            createdAt = existingMenu.createdAt,
            createdById = existingMenu.createdById,
            updatedAt = LocalDateTime.now(),
            updatedById = updatedById
        )
    }
}