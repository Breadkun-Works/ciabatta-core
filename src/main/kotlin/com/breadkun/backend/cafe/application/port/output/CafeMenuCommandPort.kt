package com.breadkun.backend.cafe.application.port.output

import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeMenuEntity

interface CafeMenuCommandPort {
    suspend fun save(cafeMenuEntity: CafeMenuEntity): CafeMenuEntity
    suspend fun update(cafeMenuEntity: CafeMenuEntity): CafeMenuEntity
    suspend fun deleteById(cafeMenuId: String): String
}