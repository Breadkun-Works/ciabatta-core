package com.breadkun.backend.application.dto

import com.breadkun.backend.global.common.enums.GlobalEnums
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CafeCartCreateDTO(
    @field:NotNull(message = "카페 위치는 필수입니다.")
    val cafeLocation: GlobalEnums.Location,

    @field:NotBlank(message = "장바구니 이름은 필수입니다.")
    @field:Size(max = 70, message = "장바구니 이름은 70자 이내여야 합니다.")
    val title: String,

    @field:Size(max = 255, message = "장바구니 설명은 255자 이내여야 합니다.")
    val description: String? = null,
)