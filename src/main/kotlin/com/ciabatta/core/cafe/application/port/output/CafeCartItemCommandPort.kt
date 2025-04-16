package com.ciabatta.core.cafe.application.port.output

import com.ciabatta.core.cafe.infrastructure.persistence.entity.CafeCartItemEntity

interface CafeCartItemCommandPort {
    suspend fun save(entity: CafeCartItemEntity): CafeCartItemEntity

    suspend fun deleteAll(ids: List<String>): Unit
}
