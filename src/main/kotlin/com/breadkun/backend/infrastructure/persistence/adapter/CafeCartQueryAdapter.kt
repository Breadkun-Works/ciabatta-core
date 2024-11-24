package com.breadkun.backend.infrastructure.persistence.adapter

import com.breadkun.backend.application.port.output.CafeCartQueryPort
import com.breadkun.backend.domain.model.enums.CafeEnums
import com.breadkun.backend.global.common.enums.GlobalEnums
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class CafeCartQueryAdapter(
    private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : CafeCartQueryPort {
    override fun findByMultipleOptions(
        cafeLocation: GlobalEnums.Location?,
        status: CafeEnums.Cart.Status?,
        createdById: String?,
        currentTime: LocalDateTime
    ): Flow<CafeCartEntity> {
        val criteriaList = mutableListOf<Criteria>()

        cafeLocation?.let {
            criteriaList.add(Criteria.where("cafe_location").`is`(it.name))
        }
        status?.let {
            val statusCriteria = when (it) {
                CafeEnums.Cart.Status.ACTIVE -> Criteria.where("expires_at").greaterThan(currentTime)
                CafeEnums.Cart.Status.INACTIVE -> Criteria.where("expires_at").lessThanOrEquals(currentTime)
            }
            criteriaList.add(statusCriteria)
        }
        createdById?.let {
            criteriaList.add(Criteria.where("created_by_id").`is`(it))
        }

        val criteria = Criteria.from(criteriaList)

        val query = Query.query(criteria)
            .sort(Sort.by(Sort.Order.desc("expires_at")))

        return r2dbcEntityTemplate.select(query, CafeCartEntity::class.java)
            .asFlow()
    }

    override suspend fun findById(
        cafeCartId: String
    ): CafeCartEntity? {
        val criteriaList = mutableListOf<Criteria>()

        criteriaList.add(Criteria.where("id").`is`(cafeCartId))

        val criteria = Criteria.from(criteriaList)

        val query = Query.query(criteria)

        return r2dbcEntityTemplate.select(query, CafeCartEntity::class.java)
            .awaitFirstOrNull()
    }
}