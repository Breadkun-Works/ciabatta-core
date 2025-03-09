package com.ciabatta.core.application.dto

import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.enums.GlobalEnums
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

data class CafeMenuUpdateDTO(
    val cafeLocation: GlobalEnums.Location? = null,

    @field:Size(max = 70, message = "Menu name must be within 70 characters.")
    val name: String? = null,

    @field:Min(1,message = "Menu price must be a positive number.")
    val price: Int? = null,

    @field:Min(1,message = "Cup deposit must be a positive number.")
    val deposit: Int? = null,

    val category: CafeEnums.Menu.Category? = null,

    val drinkTemperature: CafeEnums.Menu.Temperature? = null,

    val available: Boolean? = null,

    @field:Size(max = 255, message = "Menu description must be within 255 characters.")
    val description: String? = null,

    @field:Size(max = 100, message = "Image file name must be within 100 characters.")
    val imageFilename: String? = null,

    @field:Size(max = 255, message = "Image URL must be within 255 characters.")
    val imageUrl: String? = null
)