package com.breadkun.backend.cafe.cart.repository

import com.breadkun.backend.domain.cafe.cart.model.CafeCartItem
import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeCartItemCoroutineCrudRepository : CoroutineCrudRepository<CafeCartItem, String> {
    fun findByCafeCartId(cafeCartId: String): Flow<CafeCartItem>
}