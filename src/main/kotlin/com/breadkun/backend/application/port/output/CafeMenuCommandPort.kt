package com.breadkun.backend.application.port.output

import com.breadkun.backend.infrastructure.persistence.entity.CafeMenuEntity

interface CafeMenuCommandPort {
    suspend fun save(
        cafeMenuEntity: CafeMenuEntity
    ): CafeMenuEntity

    suspend fun update(
        cafeMenuEntity: CafeMenuEntity
    ): CafeMenuEntity

    suspend fun deleteById(
        cafeMenuId: String
    ): String
}