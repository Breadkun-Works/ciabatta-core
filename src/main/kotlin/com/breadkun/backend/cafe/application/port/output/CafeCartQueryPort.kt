package com.breadkun.backend.cafe.application.port.output

import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeCartEntity
import java.time.LocalDateTime

interface CafeCartQueryPort {
    suspend fun findActiveById(
        cafeCartId: String,
        currentTime: LocalDateTime
    ): CafeCartEntity?

    suspend fun findActiveByMultipleOptions(
        createdById: String?,
        currentTime: LocalDateTime
    ): List<CafeCartEntity>
}