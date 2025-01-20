package com.ciabatta.core.application.port.output

import com.ciabatta.core.infrastructure.persistence.entity.CafeMenuEntity

interface CafeMenuCommandPort {
    suspend fun save(
        cafeMenuEntity: CafeMenuEntity
    ): CafeMenuEntity

    suspend fun deleteById(
        cafeMenuId: Long
    ): Long
}