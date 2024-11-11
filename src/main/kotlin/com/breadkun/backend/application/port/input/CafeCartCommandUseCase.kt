package com.breadkun.backend.application.port.input

import com.breadkun.backend.domain.model.CafeCart

fun interface CafeCartCommandUseCase {
    suspend fun createCafeCart(userUUID: String, dto: com.breadkun.backend.application.dto.CafeCartCreateDTO): CafeCart
}