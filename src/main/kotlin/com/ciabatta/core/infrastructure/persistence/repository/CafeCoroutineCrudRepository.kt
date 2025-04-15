package com.ciabatta.core.infrastructure.persistence.repository

import com.ciabatta.core.infrastructure.persistence.entity.CafeCartEntity
import com.ciabatta.core.infrastructure.persistence.entity.CafeCartItemEntity
import com.ciabatta.core.infrastructure.persistence.entity.CafeMenuEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeMenuCoroutineCrudRepository : CoroutineCrudRepository<CafeMenuEntity, Long> {
    @Query("SELECT * FROM cafe_menu WHERE id IN (:ids)")
    fun findByIds(ids: Set<Long>): Flow<CafeMenuEntity>
}

interface CafeCartCoroutineCrudRepository : CoroutineCrudRepository<CafeCartEntity, String>

interface CafeCartItemCoroutineCrudRepository : CoroutineCrudRepository<CafeCartItemEntity, String> {
    fun findByCafeCartIdOrderByCreatedAtAsc(cafeCartId: String): Flow<CafeCartItemEntity>

    @Query("DELETE FROM cafe_cart_item WHERE id IN (:ids)")
    suspend fun deleteAllByIds(ids: List<String>): Unit
}
