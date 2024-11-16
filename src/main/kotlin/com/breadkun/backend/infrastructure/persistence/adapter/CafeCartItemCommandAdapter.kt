package com.breadkun.backend.infrastructure.persistence.adapter

import com.breadkun.backend.application.port.output.CafeCartItemCommandPort
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartItemEntity
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
class CafeCartItemCommandRepositoryImpl(
    private val template: R2dbcEntityTemplate
) : CafeCartItemCommandPort {
    override suspend fun saveAll(
        cafeCartItemEntities: List<CafeCartItemEntity>
    ): List<CafeCartItemEntity> {
        return Flux.fromIterable(cafeCartItemEntities)
            .flatMap { cafeCartItem ->
                template.insert(CafeCartItemEntity::class.java).using(cafeCartItem)
            }
            .collectList()
            .awaitSingle()
    }
}