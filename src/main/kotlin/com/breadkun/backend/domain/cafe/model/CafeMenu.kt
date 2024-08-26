package com.breadkun.backend.domain.cafe.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("cafe_menu")
data class CafeMenu(
    @Id
    val id: Int? = null,
    val name: String,
    val description: String?,
    val price: Int,
    val category: String,
    val available: Boolean = true,
    val imageFilename: String?,
    val imageUrl: String?
)