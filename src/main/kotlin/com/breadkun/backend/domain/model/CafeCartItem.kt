package com.breadkun.backend.domain.model

import com.breadkun.backend.application.dto.CafeCartItemCreateDTO
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity
import java.time.LocalDateTime
import java.util.*

data class CafeCartItem(
    val id: String,

    val cafeCartId: String,

    val cafeMenuId: String,

    val quantity: Int,

    val createdAt: LocalDateTime,

    val createdById: String,

    val createdByName: String
) {
    fun toEntity(
    ): CafeCartItemEntity {
        return CafeCartItemEntity(
            id = id,
            cafeCartId = cafeCartId,
            cafeMenuId = cafeMenuId,
            quantity = quantity,
            createdAt = createdAt,
            createdById = createdById,
            createdByName = createdByName
        )
    }

    companion object {
        fun fromEntity(
            cafeCartItemEntity: CafeCartItemEntity
        ): CafeCartItem {
            return CafeCartItem(
                id = cafeCartItemEntity.id,
                cafeCartId = cafeCartItemEntity.cafeCartId,
                cafeMenuId = cafeCartItemEntity.cafeMenuId,
                quantity = cafeCartItemEntity.quantity,
                createdAt = cafeCartItemEntity.createdAt,
                createdById = cafeCartItemEntity.createdById,
                createdByName = cafeCartItemEntity.createdByName
            )
        }

        fun fromCreateDTO(
            cartId: String,
            userUUID: String,
            cafeCartItemCreateDTO: CafeCartItemCreateDTO
        ): CafeCartItem {
            return CafeCartItem(
                id = UUID.randomUUID().toString(),
                cafeCartId = cartId,
                cafeMenuId = cafeCartItemCreateDTO.cafeMenuId,
                quantity = cafeCartItemCreateDTO.quantity,
                createdAt = LocalDateTime.now(),
                createdById = userUUID,
                createdByName = cafeCartItemCreateDTO.createdByName
            )
        }
    }
}