package com.ciabatta.core.application.port.input

import com.ciabatta.core.application.dto.CafeCartCreateDTO
import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.global.common.dto.DeleteIdsDTO

interface CafeCartCommandUseCase {
    suspend fun createCafeCart(
        userUUID: String,
        dto: CafeCartCreateDTO
    ): CafeCart

    suspend fun deleteCafeCarts(
        dto: DeleteIdsDTO
    )
}