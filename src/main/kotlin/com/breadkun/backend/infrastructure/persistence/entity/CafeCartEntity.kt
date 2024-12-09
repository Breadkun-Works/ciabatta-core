package com.breadkun.backend.infrastructure.persistence.entity

import com.breadkun.backend.global.common.enums.GlobalEnums
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("cafe_cart")
data class CafeCartEntity(
    @Id
    val id: String?,

    val cafeLocation: GlobalEnums.Location,

    val title: String,

    val description: String?,

    val createdAt: LocalDateTime,

    val expiresAt: LocalDateTime,

    val createdById: String
)