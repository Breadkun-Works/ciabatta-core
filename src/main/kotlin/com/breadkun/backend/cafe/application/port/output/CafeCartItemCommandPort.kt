package com.breadkun.backend.cafe.application.port.output

import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeCartItemEntity

fun interface CafeCartItemCommandPort {
    suspend fun saveAll(cafeCartItemEntities: List<CafeCartItemEntity>): List<CafeCartItemEntity>
}