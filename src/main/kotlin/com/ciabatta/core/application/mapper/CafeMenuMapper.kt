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
        cafeMenuEntity: CafeMenuEntity
    ): CafeMenu = CafeMenu(
        id = cafeMenuEntity.id,
        cafeLocation = cafeMenuEntity.cafeLocation,
        name = cafeMenuEntity.name,
        price = cafeMenuEntity.price,
        deposit = cafeMenuEntity.deposit,
        category = cafeMenuEntity.category,
        drinkTemperature = cafeMenuEntity.drinkTemperature,
        available = cafeMenuEntity.available,
        description = cafeMenuEntity.description,
        imageFilename = cafeMenuEntity.imageFilename,
        imageUrl = cafeMenuEntity.imageUrl,
        createdAt = cafeMenuEntity.createdAt,
        createdById = cafeMenuEntity.createdById,
        updatedAt = cafeMenuEntity.updatedAt,
        updatedById = cafeMenuEntity.updatedById
    )

    fun mapCreateDTOToDomain(
        userID: String,
        cafeMenuCreateDTO: CafeMenuCreateDTO
    ): CafeMenu = CafeMenu(
        id = null,
        cafeLocation = cafeMenuCreateDTO.cafeLocation,
        name = cafeMenuCreateDTO.name,
        price = cafeMenuCreateDTO.price,
        deposit = cafeMenuCreateDTO.deposit,
        category = cafeMenuCreateDTO.category,
        drinkTemperature = cafeMenuCreateDTO.drinkTemperature,
        available = true,
        description = cafeMenuCreateDTO.description,
        imageFilename = cafeMenuCreateDTO.imageFilename,
        imageUrl = cafeMenuCreateDTO.imageUrl,
        createdAt = LocalDateTime.now(),
        createdById = userID,
        updatedAt = null,
        updatedById = null
    )

    fun mapUpdateDTOToDomain(
        cafeMenuId: Long,
        userID: String,
        existingMenu: CafeMenu,
        cafeMenuUpdateDTO: CafeMenuUpdateDTO
    ): CafeMenu = CafeMenu(
        id = cafeMenuId,
        cafeLocation = cafeMenuUpdateDTO.cafeLocation ?: existingMenu.cafeLocation,
        name = cafeMenuUpdateDTO.name ?: existingMenu.name,
        price = cafeMenuUpdateDTO.price ?: existingMenu.price,
        deposit = cafeMenuUpdateDTO.deposit ?: existingMenu.deposit,
        category = cafeMenuUpdateDTO.category ?: existingMenu.category,
        drinkTemperature = cafeMenuUpdateDTO.drinkTemperature ?: existingMenu.drinkTemperature,
        available = cafeMenuUpdateDTO.available ?: existingMenu.available,
        description = cafeMenuUpdateDTO.description ?: existingMenu.description,
        imageFilename = cafeMenuUpdateDTO.imageFilename ?: existingMenu.imageFilename,
        imageUrl = cafeMenuUpdateDTO.imageUrl ?: existingMenu.imageUrl,
        createdAt = existingMenu.createdAt,
        createdById = existingMenu.createdById,
        updatedAt = LocalDateTime.now(),
        updatedById = userID
    )
}