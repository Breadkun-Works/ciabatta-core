package com.ciabatta.core.application.mapper

import com.ciabatta.core.application.dto.CafeCartCreateDTO
import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.infrastructure.persistence.entity.CafeCartEntity
import java.time.LocalDateTime

object CafeCartMapper {
    fun mapDomainToEntity(
        domain: CafeCart
    ): CafeCartEntity = CafeCartEntity(
        id = domain.id,
        cafeLocation = domain.cafeLocation,
        title = domain.title,
        description = domain.description,
        createdAt = domain.createdAt,
        expiresAt = domain.expiresAt,
        createdById = domain.createdById
    )

    fun mapEntityToDomain(
        cafeCartEntity: CafeCartEntity
    ): CafeCart = CafeCart(
        id = cafeCartEntity.id,
        cafeLocation = cafeCartEntity.cafeLocation,
        title = cafeCartEntity.title,
        description = cafeCartEntity.description,
        createdAt = cafeCartEntity.createdAt,
        expiresAt = cafeCartEntity.expiresAt,
        createdById = cafeCartEntity.createdById
    )

    fun mapCreateDTOToDomain(
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