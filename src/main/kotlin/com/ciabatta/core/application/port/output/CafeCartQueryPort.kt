package com.ciabatta.core.application.port.output

import com.ciabatta.core.infrastructure.persistence.entity.CafeCartEntity

fun interface CafeCartQueryPort {
    suspend fun findById(
        cafeCartId: String
    ): CafeCartEntity?
}