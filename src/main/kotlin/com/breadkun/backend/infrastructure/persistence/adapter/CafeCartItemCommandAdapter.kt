package com.breadkun.backend.infrastructure.persistence.adapter

import com.breadkun.backend.application.port.output.CafeCartItemCommandPort
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity
import com.breadkun.backend.infrastructure.persistence.repository.CafeCartItemCoroutineCrudRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository

@Repository
class CafeCartItemCommandRepositoryImpl(
    private val template: R2dbcEntityTemplate,
    private val cafeCartItemCoroutineCrudRepository: CafeCartItemCoroutineCrudRepository
) : CafeCartItemCommandPort {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun saveAll(
        cafeCartItemEntities: Flow<CafeCartItemEntity>
    ): Flow<CafeCartItemEntity> {
        return cafeCartItemEntities.flatMapMerge { cafeCartItem ->
            flow {
                emit(template.insert(cafeCartItem).awaitSingle())
            }
        }
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