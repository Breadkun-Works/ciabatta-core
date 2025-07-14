package com.ciabatta.core.dashboard.application.service

import com.ciabatta.core.dashboard.application.mapper.TrendingNewsMapper
import com.ciabatta.core.dashboard.application.port.input.TrendingNewsQueryUseCase
import com.ciabatta.core.dashboard.application.port.output.TrendingNewsQueryPort
import com.ciabatta.core.dashboard.domain.model.TrendingNews
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class TrendingNewsQueryService(
    private val trendingNewsQueryPort: TrendingNewsQueryPort,
) : TrendingNewsQueryUseCase {
    override suspend fun findAllTrendingNewsItems(): List<TrendingNews> =
        trendingNewsQueryPort.findAll().map { TrendingNewsMapper.mapEntityToDomain(it) }.toList()
}
