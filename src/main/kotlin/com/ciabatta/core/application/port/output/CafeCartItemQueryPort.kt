package com.ciabatta.core.application.port.output

import com.ciabatta.core.infrastructure.persistence.entity.CafeCartItemEntity
import kotlinx.coroutines.flow.Flow

interface CafeCartItemQueryPort {
    suspend fun findById(
        cafeCartItemId: String
    ): CafeCartItemEntity?

    fun findByCafeCartId(
        cafeCartId: String
    ): Flow<CafeCartItemEntity>
}