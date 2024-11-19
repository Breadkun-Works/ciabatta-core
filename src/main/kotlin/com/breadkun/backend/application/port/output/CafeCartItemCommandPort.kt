package com.breadkun.backend.application.port.output

import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity

interface CafeCartItemCommandPort {
    suspend fun saveAll(
        cafeCartItemEntities: List<CafeCartItemEntity>
    ): List<CafeCartItemEntity>

    suspend fun deleteAll(
        ids: List<String>
    )
}