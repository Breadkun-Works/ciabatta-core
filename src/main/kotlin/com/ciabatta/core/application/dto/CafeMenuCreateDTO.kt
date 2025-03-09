package com.ciabatta.core.application.dto

import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.enums.GlobalEnums
import jakarta.validation.constraints.*

data class CafeMenuCreateDTO(
    @field:NotNull(message = "Cafe location is required.")
    val cafeLocation: GlobalEnums.Location,

    @field:NotBlank(message = "Menu name is required.")
    @field:Size(max = 70, message = "Menu name must be within 70 characters.")
    val name: String,

    @field:Min(1, message = "Menu price must be a positive number.")
    val price: Int,

    @field:Min(1, message = "Cup deposit must be a positive number.")
    val deposit: Int,

    @field:NotNull(message = "Category is required.")
    val category: CafeEnums.Menu.Category,

    @field:NotNull(message = "Drink temperature is required.")
    val drinkTemperature: CafeEnums.Menu.Temperature,

    @field:Size(max = 255, message = "Menu description must be within 255 characters.")
    val description: String? = null,

    @field:NotBlank(message = "Image file is required.")
    @field:Size(max = 100, message = "Image file name must be within 100 characters.")
    val imageFilename: String,

    @field:NotBlank(message = "Image file path is required.")
    @field:Size(max = 255, message = "Image URL must be within 255 characters.")
    val imageUrl: String,
)