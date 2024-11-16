package com.breadkun.backend.application.port.output

import com.breadkun.backend.infrastructure.persistence.entity.CafeCartEntity

fun interface CafeCartCommandPort {
    suspend fun save(
        cafeCartEntity: CafeCartEntity
    ): CafeCartEntity
}