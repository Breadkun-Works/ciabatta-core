package com.breadkun.backend.cafe.infrastructure.persistence.repository

import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeCartItemEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeCartItemCoroutineCrudRepository : CoroutineCrudRepository<CafeCartItemEntity, String> {
    fun findByCafeCartId(cafeCartId: String): Flow<CafeCartItemEntity>
}