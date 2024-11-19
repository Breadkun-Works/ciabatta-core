package com.breadkun.backend.application.port.output

import com.breadkun.backend.infrastructure.persistence.entity.CafeCartEntity

interface CafeCartCommandPort {
    suspend fun save(
        cafeCartEntity: CafeCartEntity
    ): CafeCartEntity

    suspend fun deleteAll(
        ids: List<String>
    )
}