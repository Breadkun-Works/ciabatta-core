package com.breadkun.backend.domain.cafe.repository

import com.breadkun.backend.domain.cafe.model.CafeCart
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

interface CafeCartQueryRepository {
    suspend fun findActiveById(
        cafeCartId: String,
        currentTime: LocalDateTime
    ): CafeCart?

    suspend fun findActiveByMultipleOptions(
        createdById: String?,
        currentTime: LocalDateTime
    ): List<CafeCart>
}

@Repository
class CafeCartQueryRepositoryImpl(
    private val r2dbcEntityTemplate: R2dbcEntityTemplate
) : CafeCartQueryRepository {
    override suspend fun findActiveById(
        cafeCartId: String,
        currentTime: LocalDateTime
    ): CafeCart?{
        val criteriaList = mutableListOf<Criteria>()
        criteriaList.add(Criteria.where("created_at").lessThanOrEquals(currentTime))
        criteriaList.add(Criteria.where("expires_at").greaterThanOrEquals(currentTime))
        criteriaList.add(Criteria.where("id").`is`(cafeCartId))

        val criteria = Criteria.from(criteriaList)

        val query = Query.query(criteria)

        return r2dbcEntityTemplate.select(query, CafeCart::class.java)
            .awaitFirstOrNull()
    }

    override suspend fun findActiveByMultipleOptions(
        createdById: String?,
        currentTime: LocalDateTime
    ): List<CafeCart> {
        val criteriaList = mutableListOf<Criteria>()
        criteriaList.add(Criteria.where("created_at").lessThanOrEquals(currentTime))
        criteriaList.add(Criteria.where("expires_at").greaterThanOrEquals(currentTime))
        createdById?.let {
            criteriaList.add(Criteria.where("created_by_id").`is`(it))
        }

        val criteria = Criteria.from(criteriaList)

        val query = Query.query(criteria)
            .sort(Sort.by(Sort.Order.desc("expires_at")))

        return r2dbcEntityTemplate.select(query, CafeCart::class.java)
            .collectList()
            .awaitSingle()
    }
}