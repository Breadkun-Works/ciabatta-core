package com.ciabatta.core.feed.infrastructure.web.handler

import com.ciabatta.core.feed.application.port.input.DailyBakeryQueryUseCase
import com.ciabatta.core.global.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class DailyBakeryQueryHandler(
    private val dailyBakeryQueryUseCase: DailyBakeryQueryUseCase,
) {
    suspend fun findTodayBakery(request: ServerRequest): ServerResponse {
        val result = dailyBakeryQueryUseCase.findTodayBakery()

        return ResponseUtils.ok(result, "dailyBakery")
    }
}
