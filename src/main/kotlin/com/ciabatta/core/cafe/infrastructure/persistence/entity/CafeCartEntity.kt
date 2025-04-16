package com.ciabatta.core.cafe.infrastructure.persistence.entity

import com.ciabatta.core.global.enums.GlobalEnums
import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("cafe_cart")
data class CafeCartEntity(
    @Id
    val id: String?,
    val cafeLocation: GlobalEnums.Location,
    val title: String,
    val description: String?,
    val createdAt: LocalDateTime,
    val expiresAt: LocalDateTime,
    val secureShareKey: String,
    val createdById: String,
)
