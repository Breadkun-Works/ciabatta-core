package com.ciabatta.core.application.mapper

import com.ciabatta.core.domain.model.CafeMenu
import com.ciabatta.core.infrastructure.persistence.entity.CafeMenuEntity

object CafeMenuMapper {
    fun mapEntityToDomain(
        entity: CafeMenuEntity
    ): CafeMenu = CafeMenu(
        id = entity.id,
        cafeLocation = entity.cafeLocation,
        name = entity.name,
        price = entity.price,
        deposit = entity.deposit,
        category = entity.category,
        drinkTemperature = entity.drinkTemperature,
        available = entity.available,
        description = entity.description,
        imageFilename = entity.imageFilename,
        imageUrl = entity.imageUrl,
        createdAt = entity.createdAt,
        createdById = entity.createdById,
        updatedAt = entity.updatedAt,
        updatedById = entity.updatedById
    )
}