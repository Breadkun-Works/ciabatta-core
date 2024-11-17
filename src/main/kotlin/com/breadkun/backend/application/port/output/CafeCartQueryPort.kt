package com.breadkun.backend.application.port.output

import com.breadkun.backend.domain.model.enums.CafeEnums
import com.breadkun.backend.global.common.enums.GlobalEnums
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartEntity
import java.time.LocalDateTime

interface CafeCartQueryPort {
    suspend fun findByMultipleOptions(
        cafeLocation: GlobalEnums.Location?,
        status: CafeEnums.Cart.Status?,
        createdById: String?,
        currentTime: LocalDateTime
    ): List<CafeCartEntity>

    suspend fun findById(
        cafeCartId: String
    ): CafeCartEntity?
}