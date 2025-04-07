package com.ciabatta.core.application.port.output

import com.ciabatta.core.infrastructure.persistence.entity.CafeCartItemEntity

interface CafeCartItemCommandPort {
    suspend fun save(
        entity: CafeCartItemEntity
    ): CafeCartItemEntity

    suspend fun deleteAll(
        ids: List<String>
    ): Unit
}