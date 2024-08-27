package com.breadkun.backend.domain.cafe.dto.request

data class CafeMenuCreateRequestDTO(
    val name: String,
    val description: String?,
    val price: Int,
    val category: String,
    val drinkTemperature: String,
    val imageFilename: String?,
    val imageUrl: String?
)