package com.ciabatta.core.application.port.output

import com.ciabatta.core.infrastructure.persistence.entity.CafeCartEntity

interface CafeCartCommandPort {
    suspend fun save(
        cafeCartEntity: CafeCartEntity
    ): CafeCartEntity

    suspend fun deleteAll(
        ids: List<String>
    )
}