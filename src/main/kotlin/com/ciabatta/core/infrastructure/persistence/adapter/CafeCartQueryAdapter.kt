package com.ciabatta.core.infrastructure.persistence.adapter

import com.ciabatta.core.application.port.output.CafeCartQueryPort
import com.ciabatta.core.infrastructure.persistence.entity.CafeCartEntity
import com.ciabatta.core.infrastructure.persistence.repository.CafeCartCoroutineCrudRepository
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository

@Repository
class CafeCartQueryAdapter(
    private val cafeCartCoroutineCrudRepository: CafeCartCoroutineCrudRepository,
    private val r2dbcEntityTemplate: R2dbcEntityTemplate,
) : CafeCartQueryPort {
    override suspend fun findById(cafeCartId: String): CafeCartEntity? =
        cafeCartCoroutineCrudRepository.findById(cafeCartId)
}
