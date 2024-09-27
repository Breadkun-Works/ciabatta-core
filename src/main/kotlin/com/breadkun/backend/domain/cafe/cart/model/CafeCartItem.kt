package com.breadkun.backend.domain.cafe.cart.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("cafe_cart_item")
data class CafeCartItem(
    @Id
    val id: String,

    val cafeCartId: String,

    val cafeMenuId: String,

    val quantity: Int,

    val createdAt: LocalDateTime,

    val createdById: String,

    val createdByName: String
)