package com.ciabatta.core.application.mapper

import com.ciabatta.core.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.domain.model.CafeCartItem
import com.ciabatta.core.infrastructure.persistence.entity.CafeCartItemEntity
import java.time.LocalDateTime

object CafeCartItemMapper {
    fun mapDomainToEntity(
        domain: CafeCartItem
    ): CafeCartItemEntity = CafeCartItemEntity(
        id = domain.id,
        cafeCartId = domain.cafeCartId,
        cafeMenuId = domain.cafeMenuId,
        isPersonalCup = domain.isPersonalCup,
        quantity = domain.quantity,
        imageUrl = domain.imageUrl,
        createdAt = domain.createdAt,
        createdById = domain.createdById,
        createdByName = domain.createdByName
    )

    fun mapEntityToDomain(
        cafeCartItemEntity: CafeCartItemEntity
    ): CafeCartItem = CafeCartItem(
        id = cafeCartItemEntity.id,
        cafeCartId = cafeCartItemEntity.cafeCartId,
        cafeMenuId = cafeCartItemEntity.cafeMenuId,
        isPersonalCup = cafeCartItemEntity.isPersonalCup,
        quantity = cafeCartItemEntity.quantity,
        imageUrl = cafeCartItemEntity.imageUrl,
        createdAt = cafeCartItemEntity.createdAt,
        createdById = cafeCartItemEntity.createdById,
        createdByName = cafeCartItemEntity.createdByName
    )

    fun mapCreateDTOToDomain(
        cartId: String,
        userUUID: String,
        userName: String,
        cafeCartItemCreateDTO: CafeCartItemCreateDTO
    ): CafeCartItem = CafeCartItem(
        id = null,
        cafeCartId = cartId,
        cafeMenuId = cafeCartItemCreateDTO.cafeMenuId,
        isPersonalCup = cafeCartItemCreateDTO.isPersonalCup,
        quantity = cafeCartItemCreateDTO.quantity,
        imageUrl = cafeCartItemCreateDTO.imageUrl,
        createdAt = LocalDateTime.now(),
        createdById = userUUID,
        createdByName = userName
    )
}