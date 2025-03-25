package com.ciabatta.core.application.mapper

import com.ciabatta.core.application.dto.CafeMenuCreateDTO
import com.ciabatta.core.application.dto.CafeMenuUpdateDTO
import com.ciabatta.core.domain.model.CafeMenu
import com.ciabatta.core.infrastructure.persistence.entity.CafeMenuEntity
import java.time.LocalDateTime

object CafeMenuMapper {
    fun mapDomainToEntity(
        domain: CafeMenu
    ): CafeMenuEntity = CafeMenuEntity(
        id = domain.id,
        cafeLocation = domain.cafeLocation,
        name = domain.name,
        price = domain.price,
        deposit = domain.deposit,
        category = domain.category,
        drinkTemperature = domain.drinkTemperature,
        available = domain.available,
        description = domain.description,
        imageFilename = domain.imageFilename,
        imageUrl = domain.imageUrl,
        createdAt = domain.createdAt,
        createdById = domain.createdById,
        updatedAt = domain.updatedAt,
        updatedById = domain.updatedById
    )

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

    fun mapCreateDTOToDomain(
        userID: String,
        dto: CafeMenuCreateDTO
    ): CafeMenu = CafeMenu(
        id = null,
        cafeLocation = dto.cafeLocation,
        name = dto.name,
        price = dto.price,
        deposit = dto.deposit,
        category = dto.category,
        drinkTemperature = dto.drinkTemperature,
        available = true,
        description = dto.description,
        imageFilename = dto.imageFilename,
        imageUrl = dto.imageUrl,
        createdAt = LocalDateTime.now(),
        createdById = userID,
        updatedAt = null,
        updatedById = null
    )

    fun mapUpdateDTOToDomain(
        cafeMenuId: Long,
        userID: String,
        existingDomain: CafeMenu,
        dto: CafeMenuUpdateDTO
    ): CafeMenu = CafeMenu(
        id = cafeMenuId,
        cafeLocation = dto.cafeLocation ?: existingDomain.cafeLocation,
        name = dto.name ?: existingDomain.name,
        price = dto.price ?: existingDomain.price,
        deposit = dto.deposit ?: existingDomain.deposit,
        category = dto.category ?: existingDomain.category,
        drinkTemperature = dto.drinkTemperature ?: existingDomain.drinkTemperature,
        available = dto.available ?: existingDomain.available,
        description = dto.description ?: existingDomain.description,
        imageFilename = dto.imageFilename ?: existingDomain.imageFilename,
        imageUrl = dto.imageUrl ?: existingDomain.imageUrl,
        createdAt = existingDomain.createdAt,
        createdById = existingDomain.createdById,
        updatedAt = LocalDateTime.now(),
        updatedById = userID
    )
}