package com.breadkun.backend.cafe.application.port.output

import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeCartEntity

fun interface CafeCartCommandPort {
    suspend fun save(cafeCartEntity: CafeCartEntity): CafeCartEntity
}