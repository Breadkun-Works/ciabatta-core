package com.ciabatta.core.dashboard.infrastructure.persistence.repository

import com.ciabatta.core.dashboard.infrastructure.persistence.entity.TrendingNewsEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TrendingNewsCoroutineCrudRepository : CoroutineCrudRepository<TrendingNewsEntity, String>
