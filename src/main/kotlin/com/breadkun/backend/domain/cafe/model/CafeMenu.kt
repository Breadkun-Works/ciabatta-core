package com.breadkun.backend.domain.cafe.model

import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import com.breadkun.backend.domain.cafe.model.enum.CafeMenuCategory
import com.breadkun.backend.domain.cafe.model.enum.DrinkTemperature
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("cafe_menu")
data class CafeMenu(
    @Id
    val id: String,

    val cafeLocation: CafeLocation,

    val name: String,

    val price: Int,

    val category: CafeMenuCategory,

    val drinkTemperature: DrinkTemperature,

    val available: Boolean,

    val description: String?,

    val imageFilename: String?,

    val imageUrl: String?,

    val createdAt: LocalDateTime,

    val createdById: String,

    val updatedAt: LocalDateTime?,

    val updatedById: String?
)