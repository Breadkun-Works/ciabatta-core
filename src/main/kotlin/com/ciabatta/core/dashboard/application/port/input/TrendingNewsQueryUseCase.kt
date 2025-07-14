package com.ciabatta.core.dashboard.application.port.input

import com.ciabatta.core.dashboard.domain.model.TrendingNews

interface TrendingNewsQueryUseCase {
    suspend fun findAllTrendingNewsItems(): List<TrendingNews>
}
