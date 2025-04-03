package com.ciabatta.core.infrastructure.persistence.adapter

import com.ciabatta.core.application.port.output.CafeCartItemCommandPort
import com.ciabatta.core.infrastructure.persistence.entity.CafeCartItemEntity
import com.ciabatta.core.infrastructure.persistence.repository.CafeCartItemCoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
class CafeCartItemCommandRepositoryImpl(
    private val cafeCartItemCoroutineCrudRepository: CafeCartItemCoroutineCrudRepository
) : CafeCartItemCommandPort {
    override suspend fun save(
        entity: CafeCartItemEntity
    ): CafeCartItemEntity = cafeCartItemCoroutineCrudRepository.save(entity)

    override suspend fun deleteAll(
        ids: List<String>
    ) = cafeCartItemCoroutineCrudRepository.deleteAllByIds(ids)

    override suspend fun deleteAllByCafeCartId(
        cafeCartId: String
    ) = cafeCartItemCoroutineCrudRepository.deleteAllByCafeCartId(cafeCartId)
}