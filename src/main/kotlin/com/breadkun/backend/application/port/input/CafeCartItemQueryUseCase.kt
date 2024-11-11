package com.breadkun.backend.application.port.input

import com.breadkun.backend.domain.model.CafeCartItem

fun interface CafeCartItemQueryUseCase {
    suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
    ): List<CafeCartItem>
}