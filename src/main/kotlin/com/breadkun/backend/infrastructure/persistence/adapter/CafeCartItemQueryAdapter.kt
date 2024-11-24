package com.breadkun.backend.infrastructure.persistence.adapter

import com.breadkun.backend.application.port.output.CafeCartItemQueryPort
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity
import com.breadkun.backend.infrastructure.persistence.repository.CafeCartItemCoroutineCrudRepository
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