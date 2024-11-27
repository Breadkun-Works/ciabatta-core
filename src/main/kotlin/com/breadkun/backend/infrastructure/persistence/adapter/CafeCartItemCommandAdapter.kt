package com.breadkun.backend.infrastructure.persistence.adapter

import com.breadkun.backend.application.port.output.CafeCartItemCommandPort
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity
import com.breadkun.backend.infrastructure.persistence.repository.CafeCartItemCoroutineCrudRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository

@Repository
class CafeCartItemCommandRepositoryImpl(
    private val r2dbcEntityTemplate: R2dbcEntityTemplate,
    private val cafeCartItemCoroutineCrudRepository: CafeCartItemCoroutineCrudRepository
) : CafeCartItemCommandPort {
    override suspend fun save(
        cafeCartItemEntity: CafeCartItemEntity
    ): CafeCartItemEntity {
        return r2dbcEntityTemplate.insert(cafeCartItemEntity).awaitSingle()
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