package com.breadkun.backend.application.port.input

import com.breadkun.backend.application.dto.CafeCartItemCreateDTO
import com.breadkun.backend.domain.model.CafeCartItem

fun interface CafeCartItemCommandUseCase {
    suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        userName: String,
        dtos: List<CafeCartItemCreateDTO>
    ): List<CafeCartItem>
}