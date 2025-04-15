package com.ciabatta.core.application.mapper

import com.ciabatta.core.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.domain.model.CafeCartItem
import com.ciabatta.core.infrastructure.persistence.entity.CafeCartItemEntity
import java.time.LocalDateTime

object CafeCartItemMapper {
    fun mapDomainToEntity(domain: CafeCartItem): CafeCartItemEntity =
        CafeCartItemEntity(
            id = domain.id,
            cafeCartId = domain.cafeCartId,
            cafeMenuId = domain.cafeMenuId,
            isPersonalCup = domain.isPersonalCup,
            quantity = domain.quantity,
            imageUrl = domain.imageUrl,
            createdAt = domain.createdAt,
            createdById = domain.createdById,
            createdByName = domain.createdByName,
        )

    fun mapEntityToDomain(entity: CafeCartItemEntity): CafeCartItem =
        CafeCartItem(
            id = entity.id,
            cafeCartId = entity.cafeCartId,
            cafeMenuId = entity.cafeMenuId,
            isPersonalCup = entity.isPersonalCup,
            quantity = entity.quantity,
            imageUrl = entity.imageUrl,
            createdAt = entity.createdAt,
            createdById = entity.createdById,
            createdByName = entity.createdByName,
        )

    fun mapCreateDTOToDomain(
        cartId: String,
        userUUID: String,
        userName: String,
        dto: CafeCartItemCreateDTO,
    ): CafeCartItem =
        CafeCartItem(
            id = null,
            cafeCartId = cartId,
            cafeMenuId = dto.cafeMenuId,
            isPersonalCup = dto.isPersonalCup,
            quantity = dto.quantity,
            imageUrl = dto.imageUrl,
            createdAt = LocalDateTime.now(),
            createdById = userUUID,
            createdByName = userName,
        )
}
