package com.ciabatta.core.application.port.input

import com.ciabatta.core.domain.model.CafeCartItem
import com.ciabatta.core.domain.model.CafeCartItemSummary
import com.ciabatta.core.global.common.enums.GlobalEnums

interface CafeCartItemQueryUseCase {
    suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
        include: GlobalEnums.IncludeOption?
    ): List<CafeCartItem>

    suspend fun findCafeCartItemSummaryByCafeCartId(
        cafeCartId: String
    ): List<CafeCartItemSummary>
}