package com.breadkun.backend.infrastructure.persistence.repository

import com.breadkun.backend.infrastructure.persistence.entity.CafeCartEntity
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity
import com.breadkun.backend.infrastructure.persistence.entity.CafeMenuEntity
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeMenuCoroutineCrudRepository : CoroutineCrudRepository<CafeMenuEntity, String> {
    @Query("SELECT * FROM cafe_menu WHERE id IN (:ids)")
    fun findByIds(ids: Set<String>): Flow<CafeMenuEntity>
}

interface CafeCartCoroutineCrudRepository : CoroutineCrudRepository<CafeCartEntity, String> {
    @Query("DELETE FROM cafe_cart WHERE id IN (:ids)")
    suspend fun deleteAllByIds(ids: List<String>)
}

interface CafeCartItemCoroutineCrudRepository : CoroutineCrudRepository<CafeCartItemEntity, String> {
    fun findByCafeCartIdOrderByCreatedAtAsc(
        cafeCartId: String
    ): Flow<CafeCartItemEntity>

    @Query("DELETE FROM cafe_cart_item WHERE id IN (:ids)")
    suspend fun deleteAllByIds(ids: List<String>)

    suspend fun deleteAllByCafeCartId(cafeCartId: String)
}