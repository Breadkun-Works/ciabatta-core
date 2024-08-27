package com.breadkun.backend.domain.cafe.model

import com.breadkun.backend.domain.cafe.model.enum.CafeMenuCategory
import com.breadkun.backend.domain.cafe.model.enum.DrinkTemperature
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("cafe_menu")
data class CafeMenu(
    @Id
    val id: Int? = null,
    val name: String,
    val description: String?,
    val price: Int,
    val category: CafeMenuCategory,
    val drinkTemperature: DrinkTemperature,
    val available: Boolean = true,
    val imageFilename: String?,
    val imageUrl: String?
)