package com.breadkun.backend.application.port.output

import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity
import kotlinx.coroutines.flow.Flow

fun interface CafeCartItemQueryPort {
    fun findByCafeCartId(
        cafeCartId: String
    ): Flow<CafeCartItemEntity>
}