package com.breadkun.backend.application.port.output

import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity

fun interface CafeCartItemQueryPort {
    suspend fun findByCafeCartId(cafeCartId: String): List<CafeCartItemEntity>
}