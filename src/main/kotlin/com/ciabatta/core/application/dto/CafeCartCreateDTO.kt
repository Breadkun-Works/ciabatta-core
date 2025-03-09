package com.ciabatta.core.application.dto

import com.ciabatta.core.global.enums.GlobalEnums
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class CafeCartCreateDTO(
    @field:NotNull(message = "Cafe location is required")
    val cafeLocation: GlobalEnums.Location,

    @field:NotBlank(message = "Cart title is required")
    @field:Size(max = 70, message = "Cart title must be within 70 characters")
    val title: String,

    @field:Size(max = 255, message = "Cart description must be within 255 characters")
    val description: String? = null,
)