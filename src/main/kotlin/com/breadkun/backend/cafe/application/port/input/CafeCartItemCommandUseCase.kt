package com.breadkun.backend.cafe.application.port.input

import com.breadkun.backend.cafe.application.dto.CafeCartItemCreateDTO
import com.breadkun.backend.cafe.domain.model.CafeCartItem

fun interface CafeCartItemCommandUseCase {
    suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        dtos: List<CafeCartItemCreateDTO>
    ): List<CafeCartItem>
}