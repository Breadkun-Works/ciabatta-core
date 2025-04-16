package com.ciabatta.core.cafe.infrastructure.persistence.adapter

import com.ciabatta.core.cafe.application.port.output.CafeCartCommandPort
import com.ciabatta.core.cafe.infrastructure.persistence.entity.CafeCartEntity
import com.ciabatta.core.cafe.infrastructure.persistence.repository.CafeCartCoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
class CafeCartCommandAdapter(
    private val cafeCartCoroutineCrudRepository: CafeCartCoroutineCrudRepository,
) : CafeCartCommandPort {
    override suspend fun save(entity: CafeCartEntity): CafeCartEntity = cafeCartCoroutineCrudRepository.save(entity)
}
