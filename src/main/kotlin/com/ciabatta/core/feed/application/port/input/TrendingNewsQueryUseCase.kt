package com.ciabatta.core.feed.application.port.input

import com.ciabatta.core.feed.domain.model.TrendingNews

fun interface TrendingNewsQueryUseCase {
    suspend fun findAllTrendingNewsItems(): List<TrendingNews>
}
