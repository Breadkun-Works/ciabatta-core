//package com.breadkun.backend.domain.cafe.model
//
//import org.springframework.data.annotation.Id
//import org.springframework.data.relational.core.mapping.Table
//import java.time.LocalDateTime
//
//@Table("cafe_cart_item")
//data class CafeCartItem(
//    @Id
//    val id: Int? = null,
//    val cafeCartId: Int,
//    val cafeMenuId: Int,
//    val quantity: Int,
//    val createdById: String,
//    val createdByName: String,
//    val createdAt: LocalDateTime = LocalDateTime.now()
//)