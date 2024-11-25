package com.breadkun.backend.application.port.input

import com.breadkun.backend.domain.model.CafeCartItem
import com.breadkun.backend.domain.model.CafeCartItemSummary
import com.breadkun.backend.global.common.enums.GlobalEnums

interface CafeCartItemQueryUseCase {
    suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
        include: GlobalEnums.IncludeOption?
    ): List<CafeCartItem>

    suspend fun findCafeCartItemSummaryByCafeCartId(
        cafeCartId: String
    ): List<CafeCartItemSummary>
}