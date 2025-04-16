package com.ciabatta.core.cafe.application.port.input

import com.ciabatta.core.cafe.application.dto.CafeCartCreateDTO
import com.ciabatta.core.cafe.domain.model.CafeCart

interface CafeCartCommandUseCase {
    suspend fun createCafeCart(
        userUUID: String,
        dto: CafeCartCreateDTO,
    ): CafeCart

    suspend fun expireCafeCart(
        userUUID: String,
        cafeCartId: String,
    ): CafeCart
}
