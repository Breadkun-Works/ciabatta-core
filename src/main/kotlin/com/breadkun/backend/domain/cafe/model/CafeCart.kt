package com.breadkun.backend.domain.cafe.model

import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("cafe_cart")
data class CafeCart(
    @Id
    val id: String,

    val cafeLocation: CafeLocation,

    val title: String,

    val description: String?,

    val createdAt: LocalDateTime,

    val expiresAt: LocalDateTime,

    val createdById: String,

    val sharedUrl: String
)