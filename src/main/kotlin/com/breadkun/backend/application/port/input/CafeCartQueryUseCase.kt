package com.breadkun.backend.application.port.input

import com.breadkun.backend.domain.model.CafeCart

interface CafeCartQueryUseCase {
    suspend fun findActiveCafeCartsByOptions(
        createdById: String?
    ): List<CafeCart>

    suspend fun findActiveCafeCartById(
        cafeCartId: String
    ): CafeCart?
}