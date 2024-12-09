package com.breadkun.backend.application.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class CafeCartItemCreateDTO(
    @field:NotBlank(message = "카페 메뉴 ID는 필수입니다.")
    val cafeMenuId: Long,

    @field:NotBlank(message = "개인컵 사용 여부는 필수입니다.")
    val isPersonalCup: Boolean,

    @field:Positive(message = "개수는 양수여야 합니다.")
    val quantity: Int
)