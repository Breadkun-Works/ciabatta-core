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
    override fun findByCafeCartId(
        cafeCartId: String
    ): Flow<CafeCartItemEntity> {
        return cafeCartItemCoroutineCrudRepository.findByCafeCartIdOrderByCreatedAtAsc(cafeCartId)
    }
}