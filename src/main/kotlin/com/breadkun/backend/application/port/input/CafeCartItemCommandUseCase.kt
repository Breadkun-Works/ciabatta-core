package com.breadkun.backend.application.port.input

import com.breadkun.backend.domain.model.CafeCartItem

fun interface CafeCartItemCommandUseCase {
    suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        dtos: List<com.breadkun.backend.application.dto.CafeCartItemCreateDTO>
    ): List<CafeCartItem>
}