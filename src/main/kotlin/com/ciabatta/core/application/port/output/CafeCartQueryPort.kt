package com.ciabatta.core.application.port.output

import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.common.enums.GlobalEnums
import com.ciabatta.core.infrastructure.persistence.entity.CafeCartEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface CafeCartQueryPort {
    fun findByMultipleOptions(
        cafeLocation: GlobalEnums.Location?,
        status: CafeEnums.Cart.Status?,
        createdById: String?,
        currentTime: LocalDateTime
    ): Flow<CafeCartEntity>

    suspend fun findById(
        cafeCartId: String
    ): CafeCartEntity?
}