package com.ciabatta.core.domain.model

import com.ciabatta.core.application.dto.CafeMenuCreateDTO
import com.ciabatta.core.application.dto.CafeMenuUpdateDTO
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.enums.GlobalEnums
import com.ciabatta.core.infrastructure.persistence.entity.CafeMenuEntity
import java.time.LocalDateTime

data class CafeMenu(
    val id: Long?,

    val cafeLocation: GlobalEnums.Location,

    val name: String,

    val price: Int,

    val deposit: Int,

    val category: CafeEnums.Menu.Category,

    val drinkTemperature: CafeEnums.Menu.Temperature,

    val available: Boolean,

    val description: String?,

    val imageFilename: String,

    val imageUrl: String,

    val createdAt: LocalDateTime,

    val createdById: String,

    val updatedAt: LocalDateTime?,

    val updatedById: String?
) {
    fun toEntity(
    ): CafeMenuEntity {
        return CafeMenuEntity(
            id = id,
            cafeLocation = cafeLocation,
            name = name,
            price = price,
            deposit = deposit,
            category = category,
            drinkTemperature = drinkTemperature,
            available = available,
            description = description,
            imageFilename = imageFilename,
            imageUrl = imageUrl,
            createdAt = createdAt,
            createdById = createdById,
            updatedAt = updatedAt,
            updatedById = updatedById
        )
    }

    companion object {
        fun fromEntity(
            cafeMenuEntity: CafeMenuEntity
        ): CafeMenu {
            return CafeMenu(
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
        }

        fun fromCreateDTO(
            userID: String,
            cafeMenuCreateDTO: CafeMenuCreateDTO
        ): CafeMenu {
            return CafeMenu(
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
        }

        fun fromUpdateDTO(
            cafeMenuId: Long,
            userID: String,
            existingMenu: CafeMenu,
            cafeMenuUpdateDTO: CafeMenuUpdateDTO
        ): CafeMenu {
            return CafeMenu(
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
    }
}