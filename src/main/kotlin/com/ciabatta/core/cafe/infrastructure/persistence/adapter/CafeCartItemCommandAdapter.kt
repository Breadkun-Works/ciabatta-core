package com.ciabatta.core.cafe.infrastructure.persistence.adapter

import com.ciabatta.core.cafe.application.port.output.CafeCartItemCommandPort
import com.ciabatta.core.cafe.infrastructure.persistence.entity.CafeCartItemEntity
import com.ciabatta.core.cafe.infrastructure.persistence.repository.CafeCartItemCoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
class CafeCartItemCommandAdapter(
    private val cafeCartItemCoroutineCrudRepository: CafeCartItemCoroutineCrudRepository,
) : CafeCartItemCommandPort {
    override suspend fun save(entity: CafeCartItemEntity): CafeCartItemEntity =
        cafeCartItemCoroutineCrudRepository.save(entity)

    override suspend fun deleteAll(ids: List<String>): Unit = cafeCartItemCoroutineCrudRepository.deleteAllByIds(ids)
}
