package com.ciabatta.core.application.port.input

import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.common.enums.GlobalEnums

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