package com.breadkun.backend.application.port.input

import com.breadkun.backend.domain.model.CafeCart
import com.breadkun.backend.domain.model.enums.CafeEnums
import com.breadkun.backend.global.common.enums.GlobalEnums

interface CafeCartQueryUseCase {
    suspend fun findCafeCartsByOptions(
        cafeLocation: GlobalEnums.Location?,
        status: CafeEnums.Cart.Status?,
        createdById: String?
    ): List<CafeCart>

    suspend fun findCafeCartById(
        cafeCartId: String
    ): CafeCart?
}