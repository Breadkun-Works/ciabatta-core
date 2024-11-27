package com.breadkun.backend.infrastructure.persistence.adapter

import com.breadkun.backend.application.port.output.CafeMenuCommandPort
import com.breadkun.backend.infrastructure.persistence.repository.CafeMenuCoroutineCrudRepository
import com.breadkun.backend.infrastructure.persistence.entity.CafeMenuEntity
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository

@Repository
class CafeMenuCommandAdapter(
    private val cafeMenuCoroutineCrudRepository: CafeMenuCoroutineCrudRepository,
    private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : CafeMenuCommandPort {
    override suspend fun save(
        cafeMenuEntity: CafeMenuEntity
    ): CafeMenuEntity {
        return r2dbcEntityTemplate.insert(CafeMenuEntity::class.java).using(cafeMenuEntity).awaitSingle()
    }

    override suspend fun update(
        cafeMenuEntity: CafeMenuEntity
    ): CafeMenuEntity {
        return cafeMenuCoroutineCrudRepository.save(cafeMenuEntity)
    }

    override suspend fun deleteById(
        cafeMenuId: String
    ): String {
        cafeMenuCoroutineCrudRepository.deleteById(cafeMenuId)
        return cafeMenuId
    }
}