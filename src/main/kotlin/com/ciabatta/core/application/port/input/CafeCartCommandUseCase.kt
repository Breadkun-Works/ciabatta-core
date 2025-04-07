package com.ciabatta.core.application.port.input

import com.ciabatta.core.application.dto.CafeCartCreateDTO
import com.ciabatta.core.domain.model.CafeCart

interface CafeCartCommandUseCase {
    suspend fun createCafeCart(
        userUUID: String,
        dto: CafeCartCreateDTO
    ): CafeCart

    suspend fun expireCafeCart(
        userUUID: String,
        cafeCartId: String
    ): CafeCart
}