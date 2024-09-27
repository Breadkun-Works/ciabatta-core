package com.breadkun.backend.cafe.cart.repository

import com.breadkun.backend.domain.cafe.cart.model.CafeCartItem
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Repository

fun interface CafeCartItemQueryRepository {
    suspend fun findByCafeCartId(cafeCartId: String): List<CafeCartItem>
}

@Repository
class CafeCartItemQueryRepositoryImpl(
    private val cafeCartItemCoroutineCrudRepository: CafeCartItemCoroutineCrudRepository
) : CafeCartItemQueryRepository {
    override suspend fun findByCafeCartId(cafeCartId: String): List<CafeCartItem> {
        return cafeCartItemCoroutineCrudRepository.findByCafeCartId(cafeCartId).toList()
    }
}