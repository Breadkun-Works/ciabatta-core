package com.ciabatta.core.application.dto

import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.common.enums.GlobalEnums
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class CafeMenuUpdateDTO(
    val cafeLocation: GlobalEnums.Location? = null,

    @field:Size(max = 70, message = "메뉴 이름은 70자 이내여야 합니다.")
    val name: String? = null,

    @field:Positive(message = "메뉴 가격은 양수여야 합니다.")
    val price: Int? = null,

    @field:Positive(message = "컵 보증금은 양수여야 합니다.")
    val deposit: Int? = null,

    val category: CafeEnums.Menu.Category? = null,

    val drinkTemperature: CafeEnums.Menu.Temperature? = null,

    val available: Boolean? = null,

    @field:Size(max = 255, message = "메뉴 설명은 255자 이내여야 합니다.")
    val description: String? = null,

    @field:Size(max = 100, message = "이미지 파일 이름은 100자 이내여야 합니다.")
    val imageFilename: String? = null,

    @field:Size(max = 255, message = "이미지 URL은 255자 이내여야 합니다.")
    val imageUrl: String? = null
)