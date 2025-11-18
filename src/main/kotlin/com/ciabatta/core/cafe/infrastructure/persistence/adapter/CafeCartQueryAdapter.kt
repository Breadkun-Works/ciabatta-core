package com.ciabatta.core.cafe.infrastructure.persistence.adapter

import com.ciabatta.core.cafe.application.port.output.CafeCartQueryPort
import com.ciabatta.core.cafe.infrastructure.persistence.entity.CafeCartEntity
import com.ciabatta.core.cafe.infrastructure.persistence.repository.CafeCartCoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
class CafeCartQueryAdapter(
    private val cafeCartCoroutineCrudRepository: CafeCartCoroutineCrudRepository,
) : CafeCartQueryPort {
    override suspend fun findById(cafeCartId: String): CafeCartEntity? =
        cafeCartCoroutineCrudRepository.findById(cafeCartId)
}
