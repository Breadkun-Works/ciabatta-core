package com.ciabatta.core.application.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CafeCartItemCreateDTO(
    @field:Min(1, message = "Cafe menu ID is required")
    val cafeMenuId: Long,

    @field:NotNull(message = "Personal cup usage is required")
    val isPersonalCup: Boolean,

    @field:Min(1, message = "Quantity must be at least 1")
    val quantity: Int,

    @field:NotBlank(message = "Cart item image is required")
    @field:Size(max = 255, message = "Cart item image must be within 255 characters")
    val imageUrl: String,
)