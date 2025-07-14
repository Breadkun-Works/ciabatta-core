package com.ciabatta.core.dashboard.infrastructure.web.handler

import com.ciabatta.core.dashboard.application.port.input.TrendingNewsQueryUseCase
import com.ciabatta.core.global.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class TrendingNewsQueryHandler(
    private val trendingNewsQueryUseCase: TrendingNewsQueryUseCase,
) {
    suspend fun findAllTrendingNewsItems(request: ServerRequest): ServerResponse {
        val result = trendingNewsQueryUseCase.findAllTrendingNewsItems()

        return ResponseUtils.ok(result, "trendingNewsItems")
    }
}
