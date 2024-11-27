package com.breadkun.backend.application.port.output

import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity

interface CafeCartItemCommandPort {
    suspend fun save(
        cafeCartItemEntity: CafeCartItemEntity
    ): CafeCartItemEntity

    suspend fun deleteAll(
        ids: List<String>
    )

    suspend fun deleteAllByCafeCartId(
        cafeCartId: String
    )
}