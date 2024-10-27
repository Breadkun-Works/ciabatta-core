package com.breadkun.backend.cafe.application.port.input

import com.breadkun.backend.cafe.domain.model.CafeCart

interface CafeCartQueryUseCase {
    suspend fun findActiveCafeCartById(cafeCartId: String): CafeCart?
    suspend fun findActiveCafeCartsByOptions(createdById: String?): List<CafeCart>
}