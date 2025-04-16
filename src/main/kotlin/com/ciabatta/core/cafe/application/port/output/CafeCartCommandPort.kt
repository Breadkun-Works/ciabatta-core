package com.ciabatta.core.cafe.application.port.output

import com.ciabatta.core.cafe.infrastructure.persistence.entity.CafeCartEntity

fun interface CafeCartCommandPort {
    suspend fun save(entity: CafeCartEntity): CafeCartEntity
}
