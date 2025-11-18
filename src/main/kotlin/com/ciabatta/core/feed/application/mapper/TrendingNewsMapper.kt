package com.ciabatta.core.feed.application.mapper

import com.ciabatta.core.feed.domain.model.TrendingNews
import com.ciabatta.core.feed.infrastructure.persistence.entity.TrendingNewsEntity

object TrendingNewsMapper {
    fun mapEntityToDomain(entity: TrendingNewsEntity): TrendingNews =
        TrendingNews(
            id = entity.id,
            title = entity.title,
            link = entity.link,
            createdAt = entity.createdAt,
        )
}
