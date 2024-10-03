package com.breadkun.backend.cafe.application.port.input

import com.breadkun.backend.cafe.application.dto.CafeCartCreateDTO
import com.breadkun.backend.cafe.domain.model.CafeCart

fun interface CafeCartCommandUseCase {
    suspend fun createCafeCart(userUUID: String, dto: CafeCartCreateDTO): CafeCart
}