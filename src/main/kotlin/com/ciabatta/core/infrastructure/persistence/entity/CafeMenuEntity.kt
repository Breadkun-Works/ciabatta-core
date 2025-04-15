package com.ciabatta.core.infrastructure.persistence.entity

import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.enums.GlobalEnums
import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("cafe_menu")
data class CafeMenuEntity(
    @Id
    val id: Long?,
    val cafeLocation: GlobalEnums.Location,
    val name: String,
    val price: Int,
    val deposit: Int,
    val category: CafeEnums.Menu.Category,
    val drinkTemperature: CafeEnums.Menu.Temperature,
    val available: Boolean,
    val description: String?,
    val imageFilename: String,
    val imageUrl: String,
    val createdAt: LocalDateTime,
    val createdById: String,
    val updatedAt: LocalDateTime?,
    val updatedById: String?,
)
