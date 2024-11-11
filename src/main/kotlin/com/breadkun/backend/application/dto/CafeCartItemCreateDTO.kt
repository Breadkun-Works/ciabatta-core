package com.breadkun.backend.application.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class CafeCartItemCreateDTO(
    @field:NotBlank(message = "카페 메뉴 ID는 필수입니다.")
    @Schema(description = "카페 메뉴 ID")
    val cafeMenuId: String,

    @field:Positive(message = "개수는 양수여야 합니다.")
    @Schema(description = "개수")
    val quantity: Int,

    @field:NotBlank(message = "생성자 이름은 필수입니다.")
    @Schema(description = "생성자 이름")
    val createdByName: String
)