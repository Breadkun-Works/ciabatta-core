package com.breadkun.backend.infrastructure.persistence.entity

import com.breadkun.backend.domain.model.enums.CafeEnums
import com.breadkun.backend.global.common.enums.GlobalEnums
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("cafe_menu")
data class CafeMenuEntity(
    @Id
    val id: String,

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

    val updatedById: String?
)