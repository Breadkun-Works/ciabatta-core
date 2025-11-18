package com.ciabatta.core.feed.application.port.output

import com.ciabatta.core.feed.infrastructure.persistence.entity.TrendingNewsEntity
import kotlinx.coroutines.flow.Flow

fun interface TrendingNewsQueryPort {
    fun findAll(): Flow<TrendingNewsEntity>
}
