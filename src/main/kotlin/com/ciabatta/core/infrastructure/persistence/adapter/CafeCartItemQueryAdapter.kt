package com.ciabatta.core.infrastructure.persistence.adapter

import com.ciabatta.core.application.port.output.CafeCartItemQueryPort
import com.ciabatta.core.infrastructure.persistence.entity.CafeCartItemEntity
import com.ciabatta.core.infrastructure.persistence.repository.CafeCartItemCoroutineCrudRepository
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Repository

@Repository
class CafeCartItemQueryAdapter(
    private val cafeCartItemCoroutineCrudRepository: CafeCartItemCoroutineCrudRepository
) : CafeCartItemQueryPort {
    override suspend fun findById(
        id: String
    ): CafeCartItemEntity? = cafeCartItemCoroutineCrudRepository.findById(id)

    override fun findByCafeCartId(
        cafeCartId: String
    ): Flow<CafeCartItemEntity> = cafeCartItemCoroutineCrudRepository.findByCafeCartIdOrderByCreatedAtAsc(cafeCartId)
}