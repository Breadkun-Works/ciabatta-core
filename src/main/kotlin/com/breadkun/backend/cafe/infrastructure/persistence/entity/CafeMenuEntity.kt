package com.breadkun.backend.cafe.infrastructure.persistence.entity

import com.breadkun.backend.global.common.enums.Location
import com.breadkun.backend.cafe.domain.model.enums.CafeMenuCategory
import com.breadkun.backend.cafe.domain.model.enums.DrinkTemperature
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("cafe_menu")
data class CafeMenuEntity(
    @Id
    val id: String,

    val cafeLocation: Location,

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