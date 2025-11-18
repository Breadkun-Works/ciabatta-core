package com.ciabatta.core.feed.infrastructure.persistence.adapter

import com.ciabatta.core.feed.application.port.output.DailyBakeryQueryPort
import com.ciabatta.core.feed.infrastructure.persistence.entity.DailyBakeryEntity
import java.time.LocalDateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class DailyBakeryQueryAdapter(
    private val reactiveMongoTemplate: ReactiveMongoTemplate,
) : DailyBakeryQueryPort {
    override fun findAllByServedDateBetween(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
    ): Flow<DailyBakeryEntity> {
        val criteria =
            Criteria.where("servedAt")
                .gte(startDate)
                .lt(endDate)

        val query =
            Query(criteria)
                .with(Sort.by(Sort.Direction.DESC, "servedAt"))

        return reactiveMongoTemplate
            .find(query, DailyBakeryEntity::class.java)
            .asFlow()
    }
}
