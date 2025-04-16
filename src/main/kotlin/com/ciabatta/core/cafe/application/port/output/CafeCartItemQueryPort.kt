package com.ciabatta.core.cafe.application.port.output

import com.ciabatta.core.cafe.infrastructure.persistence.entity.CafeCartItemEntity
import kotlinx.coroutines.flow.Flow

interface CafeCartItemQueryPort {
    suspend fun findById(cafeCartItemId: String): CafeCartItemEntity?

    fun findByCafeCartId(cafeCartId: String): Flow<CafeCartItemEntity>
}
