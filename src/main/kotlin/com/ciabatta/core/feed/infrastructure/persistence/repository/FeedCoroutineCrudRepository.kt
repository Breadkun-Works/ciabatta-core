package com.ciabatta.core.feed.infrastructure.persistence.repository

import com.ciabatta.core.feed.infrastructure.persistence.entity.DailyBakeryEntity
import com.ciabatta.core.feed.infrastructure.persistence.entity.TrendingNewsEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface TrendingNewsCoroutineCrudRepository : CoroutineCrudRepository<TrendingNewsEntity, String>

interface DailyBakeryCoroutineCrudRepository : CoroutineCrudRepository<DailyBakeryEntity, String>
