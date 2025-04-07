package com.ciabatta.core.application.port.output

import com.ciabatta.core.infrastructure.persistence.entity.CafeCartEntity

fun interface CafeCartCommandPort {
    suspend fun save(
        entity: CafeCartEntity
    ): CafeCartEntity
}