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
        entity: CafeCartEntity
    ): CafeCart = CafeCart(
        id = entity.id,
        cafeLocation = entity.cafeLocation,
        title = entity.title,
        description = entity.description,
        createdAt = entity.createdAt,
        expiresAt = entity.expiresAt,
        createdById = entity.createdById
    )

    fun mapCreateDTOToDomain(
        userUUID: String,
        dto: CafeCartCreateDTO
    ): CafeCart {
        val createdAt = LocalDateTime.now()

        return CafeCart(
            id = null,
            cafeLocation = dto.cafeLocation,
            title = dto.title,
            description = dto.description,
            createdAt = createdAt,
            expiresAt = createdAt.plusHours(3),
            createdById = userUUID
        )
    }
}