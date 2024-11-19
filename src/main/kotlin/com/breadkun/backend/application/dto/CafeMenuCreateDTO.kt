package com.breadkun.backend.application.dto

import com.breadkun.backend.domain.model.enums.CafeEnums
import com.breadkun.backend.global.common.enums.GlobalEnums
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class CafeMenuCreateDTO(
    @field:NotNull(message = "카페 위치는 필수입니다.")
    val cafeLocation: GlobalEnums.Location,

    @field:NotBlank(message = "메뉴 이름은 필수입니다.")
    @field:Size(max = 70, message = "메뉴 이름은 70자 이내여야 합니다.")
    val name: String,

    @field:Positive(message = "메뉴 가격은 양수여야 합니다.")
    val price: Int,

    @field:Positive(message = "컵 보증금은 양수여야 합니다.")
    val deposit: Int,

    @field:NotNull(message = "카테고리는 필수입니다.")
    val category: CafeEnums.Menu.Category,

    @field:NotNull(message = "음료 온도는 필수입니다.")
    val drinkTemperature: CafeEnums.Menu.Temperature,

    @field:Size(max = 255, message = "메뉴 설명은 255자 이내여야 합니다.")
    val description: String? = null,

    @field:NotBlank(message = "이미지 파일은 필수입니다.")
    @field:Size(max = 100, message = "이미지 파일 이름은 100자 이내여야 합니다.")
    val imageFilename: String,

    @field:NotBlank(message = "이미지 파일 경로는 필수입니다.")
    @field:Size(max = 255, message = "이미지 URL은 255자 이내여야 합니다.")
    val imageUrl: String,
)