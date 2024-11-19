package com.breadkun.backend.application.port.input

import com.breadkun.backend.application.dto.CafeCartCreateDTO
import com.breadkun.backend.domain.model.CafeCart
import com.breadkun.backend.global.common.dto.DeleteIdsDTO

interface CafeCartCommandUseCase {
    suspend fun createCafeCart(
        userUUID: String,
        dto: CafeCartCreateDTO
    ): CafeCart

    suspend fun deleteCafeCarts(
        dto: DeleteIdsDTO
    )
}