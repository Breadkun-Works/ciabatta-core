package com.ciabatta.core.feed.application.service

import com.ciabatta.core.feed.application.mapper.DailyBakeryMapper
import com.ciabatta.core.feed.application.port.input.DailyBakeryQueryUseCase
import com.ciabatta.core.feed.application.port.output.DailyBakeryQueryPort
import com.ciabatta.core.feed.domain.model.DailyBakery
import java.time.LocalDate
import kotlinx.coroutines.flow.firstOrNull
import org.springframework.stereotype.Service

@Service
class DailyBakeryQueryService(
    private val dailyBakeryQueryPort: DailyBakeryQueryPort,
) : DailyBakeryQueryUseCase {
    override suspend fun findTodayBakery(): DailyBakery? {
        val now = LocalDate.now()
        val startDate = now.atStartOfDay()
        val endDate = now.plusDays(1).atStartOfDay()

        return dailyBakeryQueryPort.findAllByServedDateBetween(startDate, endDate)
            .firstOrNull()
            ?.let { DailyBakeryMapper.mapEntityToDomain(it) }
    }
}
