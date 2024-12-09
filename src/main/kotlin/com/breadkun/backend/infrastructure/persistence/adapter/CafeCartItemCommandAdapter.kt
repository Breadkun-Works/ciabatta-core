package com.breadkun.backend.infrastructure.persistence.adapter

import com.breadkun.backend.application.port.output.CafeCartItemCommandPort
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity
import com.breadkun.backend.infrastructure.persistence.repository.CafeCartItemCoroutineCrudRepository
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