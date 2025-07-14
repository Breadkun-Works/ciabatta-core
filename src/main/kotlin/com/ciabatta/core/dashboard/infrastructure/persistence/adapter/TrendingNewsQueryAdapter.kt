package com.ciabatta.core.dashboard.infrastructure.persistence.adapter

import com.ciabatta.core.dashboard.application.port.output.TrendingNewsQueryPort
import com.ciabatta.core.dashboard.infrastructure.persistence.entity.TrendingNewsEntity
import com.ciabatta.core.dashboard.infrastructure.persistence.repository.TrendingNewsCoroutineCrudRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Repository

@Repository
class TrendingNewsQueryAdapter(
    private val trendingNewsCoroutineCrudRepository: TrendingNewsCoroutineCrudRepository,
) : TrendingNewsQueryPort {
    override fun findAll(): Flow<TrendingNewsEntity> = trendingNewsCoroutineCrudRepository.findAll()
}
