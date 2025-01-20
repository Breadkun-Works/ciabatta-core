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
        cafeCartItemEntity: CafeCartItemEntity
    ): CafeCartItemEntity {
        return cafeCartItemCoroutineCrudRepository.save(cafeCartItemEntity)
    }

    override suspend fun deleteAll(
        ids: List<String>
    ) {
        return cafeCartItemCoroutineCrudRepository.deleteAllByIds(ids)
    }

    override suspend fun deleteAllByCafeCartId(
        cafeCartId: String
    ) {
        return cafeCartItemCoroutineCrudRepository.deleteAllByCafeCartId(cafeCartId)
    }
}