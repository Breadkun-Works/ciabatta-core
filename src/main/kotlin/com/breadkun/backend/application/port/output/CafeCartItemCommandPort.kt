package com.breadkun.backend.application.port.output

import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity
import kotlinx.coroutines.flow.Flow

interface CafeCartItemCommandPort {
    fun saveAll(
        cafeCartItemEntities: Flow<CafeCartItemEntity>
    ): Flow<CafeCartItemEntity>

    suspend fun deleteAll(
        ids: List<String>
    )
}