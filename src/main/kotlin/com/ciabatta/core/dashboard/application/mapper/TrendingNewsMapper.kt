package com.ciabatta.core.dashboard.application.mapper

import com.ciabatta.core.dashboard.domain.model.TrendingNews
import com.ciabatta.core.dashboard.infrastructure.persistence.entity.TrendingNewsEntity

object TrendingNewsMapper {
    fun mapEntityToDomain(entity: TrendingNewsEntity): TrendingNews =
        TrendingNews(
            id = entity.id,
            title = entity.title,
            link = entity.link,
            createdAt = entity.createdAt,
        )
}
