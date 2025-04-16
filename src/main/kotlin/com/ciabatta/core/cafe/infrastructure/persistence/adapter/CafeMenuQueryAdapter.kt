package com.ciabatta.core.cafe.infrastructure.persistence.adapter

import com.ciabatta.core.cafe.application.port.output.CafeMenuQueryPort
import com.ciabatta.core.cafe.domain.model.CafeMenuBoard
import com.ciabatta.core.cafe.domain.model.CafeMenuBoardOptionDTO
import com.ciabatta.core.cafe.domain.model.enums.CafeEnums
import com.ciabatta.core.cafe.infrastructure.persistence.entity.CafeMenuEntity
import com.ciabatta.core.cafe.infrastructure.persistence.repository.CafeMenuCoroutineCrudRepository
import com.ciabatta.core.global.enums.GlobalEnums
import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.postgresql.codec.Json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class CafeMenuQueryAdapter(
    private val cafeMenuCoroutineCrudRepository: CafeMenuCoroutineCrudRepository,
    private val databaseClient: DatabaseClient,
    private val objectMapper: ObjectMapper,
) : CafeMenuQueryPort {
    override suspend fun findById(id: Long): CafeMenuEntity? = cafeMenuCoroutineCrudRepository.findById(id)

    override fun findByIds(ids: Set<Long>): Flow<CafeMenuEntity> {
        if (ids.isEmpty()) return emptyFlow()

        return cafeMenuCoroutineCrudRepository.findByIds(ids)
    }

    override fun findByMultipleOptionsWithGrouping(
        cafeLocation: GlobalEnums.Location?,
        name: String?,
        category: CafeEnums.Menu.Category?,
        page: Int?,
        size: Int?,
    ): Flow<CafeMenuBoard> {
        val baseQuery =
            """
            WITH grouped_data AS (
                SELECT cafe_location, name, category,
                    json_agg(
                        json_build_object(
                            'drinkTemperature', drink_temperature,
                            'id', id,
                            'available', available,
                            'price', price,
                            'deposit', deposit,
                            'description', description,
                            'imageFilename', image_filename,
                            'imageUrl', image_url
                        )
                        ORDER BY drink_temperature
                    ) AS options
                FROM
                    public.cafe_menu
                WHERE 1=1
                ${if (cafeLocation != null) "AND cafe_location = :cafeLocation" else ""}
                ${if (name != null) "AND name LIKE :name" else ""}
                ${if (category != null) "AND category = :category" else ""}
                GROUP BY cafe_location, name, category
            )
            SELECT * FROM grouped_data
            ORDER BY cafe_location, category, name
            ${if (page != null && size != null) "LIMIT :size OFFSET :offset" else ""};
            """.trimIndent()

        var querySpec = databaseClient.sql(baseQuery)
        cafeLocation?.let { querySpec = querySpec.bind("cafeLocation", it.name) }
        name?.let { querySpec = querySpec.bind("name", "%$it%") }
        category?.let { querySpec = querySpec.bind("category", it.name) }

        if (page != null && size != null) {
            querySpec = querySpec.bind("size", size).bind("offset", page * size)
        }

        return querySpec.map { row, _ ->
            val options: List<CafeMenuBoardOptionDTO> =
                objectMapper.readValue(
                    (row["options"] as Json).asString(),
                    objectMapper.typeFactory.constructCollectionType(
                        List::class.java,
                        CafeMenuBoardOptionDTO::class.java,
                    ),
                )
            CafeMenuBoard(
                cafeLocation = GlobalEnums.Location.valueOf(row["cafe_location"] as String),
                name = row["name"] as String,
                category = CafeEnums.Menu.Category.valueOf(row["category"] as String),
                options = options,
            )
        }
            .all()
            .asFlow()
    }

    override suspend fun countByMultipleOptionsWithGrouping(
        cafeLocation: GlobalEnums.Location?,
        name: String?,
        category: CafeEnums.Menu.Category?,
    ): Long {
        val baseQuery =
            """
            SELECT COUNT(*) AS count
            FROM (
                SELECT cafe_location, name, category
                FROM cafe_menu
                WHERE 1=1
                ${if (cafeLocation != null) "AND cafe_location = :cafeLocation" else ""}
                ${if (name != null) "AND name LIKE :name" else ""}
                ${if (category != null) "AND category = :category" else ""}
                GROUP BY cafe_location, name, category
            ) AS grouped_data
            """.trimIndent()

        var querySpec = databaseClient.sql(baseQuery)
        cafeLocation?.let { querySpec = querySpec.bind("cafeLocation", it.name) }
        name?.let { querySpec = querySpec.bind("name", "%$it%") }
        category?.let { querySpec = querySpec.bind("category", it.name) }

        return querySpec
            .map { row, _ -> row["count"] as Long }
            .one()
            .awaitSingle()
    }
}
