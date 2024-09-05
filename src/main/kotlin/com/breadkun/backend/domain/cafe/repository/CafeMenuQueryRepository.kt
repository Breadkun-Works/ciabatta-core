package com.breadkun.backend.domain.cafe.repository

import com.breadkun.backend.domain.cafe.model.CafeMenu
import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import com.breadkun.backend.domain.cafe.model.enum.CafeMenuCategory
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria
import org.springframework.data.relational.core.query.Query
import org.springframework.r2dbc.core.DatabaseClient
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

    suspend fun countByMultipleOptionsWithGrouping(
        cafeLocation: CafeLocation?,
        name: String?,
        category: CafeMenuCategory?
    ): Long
}

@Repository
class CafeMenuQueryRepositoryImpl(
    private val cafeMenuCoroutineCrudRepository: CafeMenuCoroutineCrudRepository,
    private val template: R2dbcEntityTemplate,
    private val databaseClient: DatabaseClient
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
        var criteria = Criteria.empty()

        if (cafeLocation != null) {
            criteria = criteria.and("cafe_location").`is`(cafeLocation.name)
        }
        if (name != null) {
            criteria = criteria.and("name").like("%$name%")
        }
        if (category != null) {
            criteria = criteria.and("category").`is`(category.name)
        }

        val query = Query.query(criteria)

        if (page != null && size != null) {
            query.limit(size).offset((page * size).toLong())
        }

        query.sort(Sort.by(Sort.Order.asc("cafe_location"), Sort.Order.asc("name"), Sort.Order.asc("category")))

        return template.select(query, CafeMenu::class.java).collectList().awaitSingle()
    }

    override suspend fun countByMultipleOptionsWithGrouping(
        cafeLocation: CafeLocation?,
        name: String?,
        category: CafeMenuCategory?
    ): Long {
        val baseQuery = """
        SELECT COUNT(*) AS count
        FROM (
            SELECT cafe_location, name, category
            FROM cafe_menu
            WHERE 1=1
    """.trimIndent()

        val queryBuilder = StringBuilder(baseQuery)

        cafeLocation?.let {
            queryBuilder.append(" AND cafe_location = :cafeLocation")
        }
        name?.let {
            queryBuilder.append(" AND name LIKE :name")
        }
        category?.let {
            queryBuilder.append(" AND category = :category")
        }

        queryBuilder.append(" GROUP BY cafe_location, name, category) AS grouped_data")

        val sqlSpec = databaseClient.sql(queryBuilder.toString())

        var boundSqlSpec = sqlSpec
        cafeLocation?.let {
            boundSqlSpec = boundSqlSpec.bind("cafeLocation", it.name)
        }
        name?.let {
            boundSqlSpec = boundSqlSpec.bind("name", "%$it%")
        }
        category?.let {
            boundSqlSpec = boundSqlSpec.bind("category", it.name)
        }

        return boundSqlSpec
            .map { row, _ -> row["count"] as Long }
            .one()
            .awaitSingle()
    }
}