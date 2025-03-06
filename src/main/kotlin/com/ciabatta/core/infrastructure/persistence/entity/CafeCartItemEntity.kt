package com.ciabatta.core.infrastructure.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

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

    val createdByName: String
)