package com.breadkun.backend.infrastructure.persistence.adapter

import com.breadkun.backend.domain.model.CafeMenuBoard
import com.breadkun.backend.domain.model.CafeMenuBoardOptionDTO
import com.breadkun.backend.application.port.output.CafeMenuQueryPort
import com.breadkun.backend.infrastructure.persistence.entity.CafeMenuEntity
import com.breadkun.backend.global.common.enums.Location
import com.breadkun.backend.domain.model.enums.CafeMenuCategory
import com.breadkun.backend.infrastructure.persistence.repository.CafeMenuCoroutineCrudRepository
import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.postgresql.codec.Json
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

@Repository
class CafeMenuQueryAdapter(
    private val cafeMenuCoroutineCrudRepository: CafeMenuCoroutineCrudRepository,
    private val databaseClient: DatabaseClient,
    private val objectMapper: ObjectMapper
) : CafeMenuQueryPort {
    override suspend fun findById(id: String): CafeMenuEntity? {
        return cafeMenuCoroutineCrudRepository.findById(id)
    }

    override suspend fun findByMultipleOptionsWithGrouping(
        cafeLocation: Location?,
        name: String?,
        category: CafeMenuCategory?,
        page: Int?,
        size: Int?
    ): List<CafeMenuBoard> {
        val baseQuery = """
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
            val options: List<CafeMenuBoardOptionDTO> = objectMapper.readValue(
                (row["options"] as Json).asString(),
                objectMapper.typeFactory.constructCollectionType(List::class.java, CafeMenuBoardOptionDTO::class.java)
            )
            CafeMenuBoard(
                cafeLocation = Location.valueOf(row["cafe_location"] as String),
                name = row["name"] as String,
                category = CafeMenuCategory.valueOf(row["category"] as String),
                options = options
            )
        }
            .all()
            .collectList()
            .awaitSingle()
    }


    override suspend fun countByMultipleOptionsWithGrouping(
        cafeLocation: Location?,
        name: String?,
        category: CafeMenuCategory?
    ): Long {
        val baseQuery = """
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