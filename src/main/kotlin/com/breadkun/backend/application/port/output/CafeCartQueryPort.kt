package com.breadkun.backend.application.port.output

import com.breadkun.backend.domain.model.enums.CafeEnums
import com.breadkun.backend.global.common.enums.GlobalEnums
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartEntity
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