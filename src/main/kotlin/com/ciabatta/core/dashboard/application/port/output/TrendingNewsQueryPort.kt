package com.ciabatta.core.dashboard.application.port.output

import com.ciabatta.core.dashboard.infrastructure.persistence.entity.TrendingNewsEntity
import kotlinx.coroutines.flow.Flow

interface TrendingNewsQueryPort {
    fun findAll(): Flow<TrendingNewsEntity>
}
