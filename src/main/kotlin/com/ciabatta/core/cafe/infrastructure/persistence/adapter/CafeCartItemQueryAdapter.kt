package com.ciabatta.core.cafe.infrastructure.persistence.adapter

import com.ciabatta.core.cafe.application.port.output.CafeCartItemQueryPort
import com.ciabatta.core.cafe.infrastructure.persistence.entity.CafeCartItemEntity
import com.ciabatta.core.cafe.infrastructure.persistence.repository.CafeCartItemCoroutineCrudRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Repository

@Repository
class CafeCartItemQueryAdapter(
    private val cafeCartItemCoroutineCrudRepository: CafeCartItemCoroutineCrudRepository,
) : CafeCartItemQueryPort {
    override suspend fun findById(cafeCartItemId: String): CafeCartItemEntity? =
        cafeCartItemCoroutineCrudRepository.findById(cafeCartItemId)

    override fun findByCafeCartId(cafeCartId: String): Flow<CafeCartItemEntity> =
        cafeCartItemCoroutineCrudRepository.findByCafeCartIdOrderByCreatedAtAsc(cafeCartId)
}
