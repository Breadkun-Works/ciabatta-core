package com.breadkun.backend.cafe.application.port.input

import com.breadkun.backend.cafe.domain.model.CafeCartItem

fun interface CafeCartItemQueryUseCase {
    suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
    ): List<CafeCartItem>
}