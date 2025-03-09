package com.ciabatta.core.domain.model

import com.ciabatta.core.application.dto.CafeCartCreateDTO
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.enums.GlobalEnums
import com.ciabatta.core.infrastructure.persistence.entity.CafeCartEntity
import java.time.LocalDateTime

data class CafeCart(
    val id: String?,

    val cafeLocation: GlobalEnums.Location,

    val title: String,

    val description: String?,

    val createdAt: LocalDateTime,

    val expiresAt: LocalDateTime,

    val createdById: String
) {
    val status: CafeEnums.Cart.Status
        get() {
            val now = LocalDateTime.now()
            return when {
                now.isBefore(expiresAt) -> CafeEnums.Cart.Status.ACTIVE
                else -> CafeEnums.Cart.Status.INACTIVE
            }
        }

    fun toEntity(
    ): CafeCartEntity {
        return CafeCartEntity(
            id = id,
            cafeLocation = cafeLocation,
            title = title,
            description = description,
            createdAt = createdAt,
            expiresAt = expiresAt,
            createdById = createdById
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
                createdById = cafeCartEntity.createdById
            )
        }

        fun fromCreateDTO(
            userUUID: String,
            cafeCartCreateDTO: CafeCartCreateDTO
        ): CafeCart {
            val createdAt = LocalDateTime.now()

            return CafeCart(
                id = null,
                cafeLocation = cafeCartCreateDTO.cafeLocation,
                title = cafeCartCreateDTO.title,
                description = cafeCartCreateDTO.description,
                createdAt = createdAt,
                expiresAt = createdAt.plusHours(3),
                createdById = userUUID
            )
        }
    }
}