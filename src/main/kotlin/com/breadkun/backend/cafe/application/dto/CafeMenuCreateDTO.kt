package com.breadkun.backend.cafe.application.dto

import com.breadkun.backend.global.common.enums.Location
import com.breadkun.backend.cafe.domain.model.enums.CafeMenuCategory
import com.breadkun.backend.cafe.domain.model.enums.DrinkTemperature
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class CafeMenuCreateDTO(
    @field:NotNull(message = "카페 위치는 필수입니다.")
    @Schema(description = "카페의 위치", example = "KANGCHON")
    val cafeLocation: Location,

    @field:NotBlank(message = "메뉴 이름은 필수입니다.")
    @field:Size(max = 70, message = "메뉴 이름은 70자 이내여야 합니다.")
    @Schema(description = "메뉴의 이름", example = "아메리카노")
    val name: String,

    @field:Positive(message = "메뉴 가격은 양수여야 합니다.")
    @Schema(description = "메뉴의 가격", example = "4500")
    val price: Int,

    @field:NotNull(message = "카테고리는 필수입니다.")
    @Schema(description = "메뉴의 카테고리", example = "COFFEE")
    val category: CafeMenuCategory,

    @field:NotNull(message = "음료 온도는 필수입니다.")
    @Schema(description = "음료의 온도", example = "HOT")
    val drinkTemperature: DrinkTemperature,

    @field:Size(max = 255, message = "메뉴 설명은 255자 이내여야 합니다.")
    @Schema(description = "메뉴에 대한 설명", example = "신선한 원두로 만든 아메리카노")
    val description: String? = null,

    @field:Size(max = 100, message = "이미지 파일 이름은 100자 이내여야 합니다.")
    @Schema(description = "이미지 파일 이름", example = "americano.png")
    val imageFilename: String? = null,

    @field:Size(max = 255, message = "이미지 URL은 255자 이내여야 합니다.")
    @Schema(description = "이미지의 URL", example = "http://example.com/americano.png")
    val imageUrl: String? = null,
)