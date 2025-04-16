package com.ciabatta.core.cafe.infrastructure.persistence.entity

import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("cafe_cart_item")
data class CafeCartItemEntity(
    @Id
    val id: String?,
    val cafeCartId: String,
    val cafeMenuId: Long,
    val isPersonalCup: Boolean,
    val quantity: Int,
    val imageUrl: String,
    val createdAt: LocalDateTime,
    val createdById: String,
    val createdByName: String,
)
