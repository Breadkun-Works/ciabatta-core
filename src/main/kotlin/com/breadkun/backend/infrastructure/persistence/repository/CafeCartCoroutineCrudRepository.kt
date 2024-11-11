package com.breadkun.backend.infrastructure.persistence.repository

import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeCartItemCoroutineCrudRepository : CoroutineCrudRepository<CafeCartItemEntity, String> {
    fun findByCafeCartId(cafeCartId: String): Flow<CafeCartItemEntity>
}