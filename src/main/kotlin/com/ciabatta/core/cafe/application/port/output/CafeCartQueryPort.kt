package com.ciabatta.core.cafe.application.port.output

import com.ciabatta.core.cafe.infrastructure.persistence.entity.CafeCartEntity

interface CafeCartQueryPort {
    suspend fun findById(cafeCartId: String): CafeCartEntity?
}
