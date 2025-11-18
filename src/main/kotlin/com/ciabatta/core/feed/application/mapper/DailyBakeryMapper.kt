package com.ciabatta.core.feed.application.mapper

import com.ciabatta.core.feed.domain.model.DailyBakery
import com.ciabatta.core.feed.infrastructure.persistence.entity.DailyBakeryEntity

object DailyBakeryMapper {
    fun mapEntityToDomain(entity: DailyBakeryEntity): DailyBakery =
        DailyBakery(
            id = entity.id,
            name = entity.name,
            imageUrl = entity.imageUrl,
            servedAt = entity.servedAt,
            createdAt = entity.createdAt,
            createdById = entity.createdById,
        )
}
