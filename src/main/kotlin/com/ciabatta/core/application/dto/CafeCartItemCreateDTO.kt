package com.ciabatta.core.application.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class CafeCartItemCreateDTO(
    @field:NotBlank(message = "Cafe menu ID is required")
    val cafeMenuId: Long,

    @field:NotBlank(message = "Personal cup usage is required")
    val isPersonalCup: Boolean,

    @field:Positive(message = "Quantity must be a positive number")
    val quantity: Int,

    @field:NotBlank(message = "Cart item image is required")
    val imageUrl: String,
)