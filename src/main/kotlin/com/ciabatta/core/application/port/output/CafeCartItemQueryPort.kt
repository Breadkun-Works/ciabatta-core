package com.ciabatta.core.application.port.output

import com.ciabatta.core.infrastructure.persistence.entity.CafeCartItemEntity
import kotlinx.coroutines.flow.Flow

fun interface CafeCartItemQueryPort {
    fun findByCafeCartId(
        cafeCartId: String
    ): Flow<CafeCartItemEntity>
}