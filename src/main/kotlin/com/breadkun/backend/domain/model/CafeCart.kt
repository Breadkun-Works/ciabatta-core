package com.breadkun.backend.domain.model

import com.breadkun.backend.application.dto.CafeCartCreateDTO
import com.breadkun.backend.global.common.enums.GlobalEnums
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartEntity
import java.time.LocalDateTime
import java.util.*

data class CafeCart(
    val id: String,

    val cafeLocation: GlobalEnums.Location,

    val title: String,

    val description: String?,

    val createdAt: LocalDateTime,

    val expiresAt: LocalDateTime,

    val createdById: String,

    val sharedUrl: String
) {
    fun toEntity(
    ): CafeCartEntity {
        return CafeCartEntity(
            id = id,
            cafeLocation = cafeLocation,
            title = title,
            description = description,
            createdAt = createdAt,
            expiresAt = expiresAt,
            createdById = createdById,
            sharedUrl = sharedUrl
        )
    }

    companion object {
        fun fromEntity(
            cafeCartEntity: CafeCartEntity
        ): CafeCart {
            return CafeCart(
                id = cafeCartEntity.id,
                cafeLocation = cafeCartEntity.cafeLocation,
                title = cafeCartEntity.title,
                description = cafeCartEntity.description,
                createdAt = cafeCartEntity.createdAt,
                expiresAt = cafeCartEntity.expiresAt,
                createdById = cafeCartEntity.createdById,
                sharedUrl = cafeCartEntity.sharedUrl
            )
        }

        fun fromCreateDTO(
            userUUID: String,
            cafeCartCreateDTO: CafeCartCreateDTO
        ): CafeCart {
            val id = UUID.randomUUID().toString()
            val createdAt = LocalDateTime.now()

            return CafeCart(
                id = id,
                cafeLocation = cafeCartCreateDTO.cafeLocation,
                title = cafeCartCreateDTO.title,
                description = cafeCartCreateDTO.description,
                createdAt = createdAt,
                expiresAt = createdAt.plusHours(3),
                createdById = userUUID,
                sharedUrl = "https://breadkun.com/cafe/carts/$id"
            )
        }
    }
}