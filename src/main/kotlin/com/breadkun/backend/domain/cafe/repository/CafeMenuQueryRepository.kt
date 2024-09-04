package com.breadkun.backend.domain.cafe.repository

import com.breadkun.backend.domain.cafe.model.CafeMenu
import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import com.breadkun.backend.domain.cafe.model.enum.CafeMenuCategory
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.stereotype.Repository

interface CafeMenuQueryRepository {
    suspend fun findById(id: String): CafeMenu?
    suspend fun findByMultipleOptionsWithPaging(
        cafeLocation: CafeLocation?,
        name: String?,
        category: CafeMenuCategory?,
        page: Int?,
        size: Int?
    ): List<CafeMenu>
}

@Repository
class CafeMenuQueryRepositoryImpl(
    private val cafeMenuCoroutineCrudRepository: CafeMenuCoroutineCrudRepository,
    private val template: R2dbcEntityTemplate
) : CafeMenuQueryRepository {
    override suspend fun findById(id: String): CafeMenu? {
        return cafeMenuCoroutineCrudRepository.findById(id)
    }

    override suspend fun findByMultipleOptionsWithPaging(
        cafeLocation: CafeLocation?,
        name: String?,
        category: CafeMenuCategory?,
        page: Int?,
        size: Int?
    ): List<CafeMenu> {
        val criteria = Criteria.empty().apply {
            cafeLocation?.let { and("cafe_location").`is`(it.name) }
            name?.let { and("name").`is`(it) }
            category?.let { and("category").`is`(it.name) }
        }

        val query = Query.query(criteria).apply {
            if (page != null && size != null) {
                limit(size).offset((page * size).toLong())
            }
        }

        return template.select(query, CafeMenu::class.java).collectList().awaitSingle()
    }
}