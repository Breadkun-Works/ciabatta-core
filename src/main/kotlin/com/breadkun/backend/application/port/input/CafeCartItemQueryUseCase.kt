package com.breadkun.backend.application.port.input

import com.breadkun.backend.domain.model.CafeCartItem
import com.breadkun.backend.global.common.enums.GlobalEnums

fun interface CafeCartItemQueryUseCase {
    suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
        include: GlobalEnums.IncludeOption?
    ): List<CafeCartItem>
}