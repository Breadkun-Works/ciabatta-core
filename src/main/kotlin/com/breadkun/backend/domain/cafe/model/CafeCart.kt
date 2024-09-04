//package com.breadkun.backend.domain.cafe.model
//
//import org.springframework.data.annotation.Id
//import org.springframework.data.relational.core.mapping.Table
//import java.time.LocalDateTime
//
//@Table("cafe_cart")
//data class CafeCart(
//    @Id
//    val id: Int? = null,
//    val title: String,
//    val description: String?,
//    val createdAt: LocalDateTime = LocalDateTime.now(),
//    val expiresAt: LocalDateTime,
//    val sharedUrl: String,
//    val createdById: String
//)