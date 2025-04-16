package com.ciabatta.core.cafe.application.port.input

import com.ciabatta.core.cafe.domain.model.CafeCartItem
import com.ciabatta.core.global.enums.GlobalEnums

interface CafeCartItemQueryUseCase {
    suspend fun findCafeCartItemsById(id: String): CafeCartItem?

    suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
        include: GlobalEnums.IncludeOption?,
    ): List<CafeCartItem>

    suspend fun fetchDetails(cafeCartItems: List<CafeCartItem>): List<CafeCartItem>
}
