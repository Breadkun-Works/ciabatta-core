package com.ciabatta.core.feed.application.port.output

import com.ciabatta.core.feed.infrastructure.persistence.entity.DailyBakeryEntity
import java.time.LocalDateTime
import kotlinx.coroutines.flow.Flow

interface DailyBakeryQueryPort {
    fun findAllByServedDateBetween(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
    ): Flow<DailyBakeryEntity>
}
