package com.ciabatta.core.feed.infrastructure.persistence.adapter

import com.ciabatta.core.feed.application.port.output.TrendingNewsQueryPort
import com.ciabatta.core.feed.infrastructure.persistence.entity.TrendingNewsEntity
import com.ciabatta.core.feed.infrastructure.persistence.repository.TrendingNewsCoroutineCrudRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Repository

@Repository
class TrendingNewsQueryAdapter(
    private val trendingNewsCoroutineCrudRepository: TrendingNewsCoroutineCrudRepository,
) : TrendingNewsQueryPort {
    override fun findAll(): Flow<TrendingNewsEntity> = trendingNewsCoroutineCrudRepository.findAll()
}
