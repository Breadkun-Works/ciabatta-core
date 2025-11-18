package com.ciabatta.core.feed.application.port.input

import com.ciabatta.core.feed.domain.model.DailyBakery

fun interface DailyBakeryQueryUseCase {
    suspend fun findTodayBakery(): DailyBakery?
}
