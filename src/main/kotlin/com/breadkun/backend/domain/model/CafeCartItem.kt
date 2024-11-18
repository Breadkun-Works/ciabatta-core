package com.breadkun.backend.domain.model

import com.breadkun.backend.application.dto.CafeCartItemCreateDTO
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity
import java.time.LocalDateTime
import java.util.*

data class CafeCartItem(
    val id: String,

    val cafeCartId: String,

    val cafeMenuId: String,

    val isPersonalCup: Boolean,

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
            isPersonalCup = isPersonalCup,
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
                isPersonalCup = cafeCartItemEntity.isPersonalCup,
                quantity = cafeCartItemEntity.quantity,
                createdAt = cafeCartItemEntity.createdAt,
                createdById = cafeCartItemEntity.createdById,
                createdByName = cafeCartItemEntity.createdByName
            )
        }

        fun fromCreateDTO(
            cartId: String,
            userUUID: String,
            userName: String,
            cafeCartItemCreateDTO: CafeCartItemCreateDTO
        ): CafeCartItem {
            return CafeCartItem(
                id = UUID.randomUUID().toString(),
                cafeCartId = cartId,
                cafeMenuId = cafeCartItemCreateDTO.cafeMenuId,
                isPersonalCup = cafeCartItemCreateDTO.isPersonalCup,
                quantity = cafeCartItemCreateDTO.quantity,
                createdAt = LocalDateTime.now(),
                createdById = userUUID,
                createdByName = userName
            )
        }
    }
}