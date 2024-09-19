package com.breadkun.backend.domain.cafe.repository

import com.breadkun.backend.domain.cafe.model.CafeCart
import com.breadkun.backend.domain.cafe.model.CafeCartItem
import com.breadkun.backend.domain.cafe.model.CafeMenu
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.time.LocalDateTime

interface CafeMenuCoroutineCrudRepository : CoroutineCrudRepository<CafeMenu, String> {}

interface CafeCartCoroutineCrudRepository : CoroutineCrudRepository<CafeCart, String> {
    fun findByCreatedByIdAndCreatedAtLessThanEqualAndExpiresAtGreaterThanEqual(
        createdById: String,
        currentTimeForCreatedAt: LocalDateTime,
        currentTimeForExpiresAt: LocalDateTime
    ): Flow<CafeCart>
}

interface CafeCartItemCoroutineCrudRepository : CoroutineCrudRepository<CafeCartItem, String> {
    fun findByCafeCartId(cafeCartId: String): Flow<CafeCartItem>
}