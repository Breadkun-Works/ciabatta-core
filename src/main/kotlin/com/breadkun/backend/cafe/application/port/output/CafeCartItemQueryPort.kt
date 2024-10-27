package com.breadkun.backend.cafe.application.port.output

import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeCartItemEntity

fun interface CafeCartItemQueryPort {
    suspend fun findByCafeCartId(cafeCartId: String): List<CafeCartItemEntity>
}