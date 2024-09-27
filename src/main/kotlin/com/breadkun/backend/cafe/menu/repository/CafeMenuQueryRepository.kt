package com.breadkun.backend.cafe.menu.repository

import com.breadkun.backend.domain.cafe.menu.dto.response.CafeMenuBoardDTO
import com.breadkun.backend.domain.cafe.menu.dto.response.CafeMenuBoardOptionDTO
import com.breadkun.backend.cafe.menu.model.CafeMenu
import com.breadkun.backend.global.common.enums.Location
import com.breadkun.backend.domain.cafe.menu.model.enums.CafeMenuCategory
import com.fasterxml.jackson.databind.ObjectMapper
import io.r2dbc.postgresql.codec.Json
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository

interface CafeMenuQueryRepository {
    suspend fun findById(id: String): com.breadkun.backend.cafe.menu.model.CafeMenu?
    suspend fun findByMultipleOptionsWithGrouping(
        cafeLocation: Location?,
        name: String?,
        category: CafeMenuCategory?,
        page: Int?,
        size: Int?
    ): List<CafeMenuBoardDTO>

    suspend fun countByMultipleOptionsWithGrouping(
        cafeLocation: Location?,
        name: String?,
        category: CafeMenuCategory?
    ): Long
}

@Repository
class CafeMenuQueryRepositoryImpl(
    private val cafeMenuCoroutineCrudRepository: CafeMenuCoroutineCrudRepository,
    private val databaseClient: DatabaseClient,
    private val objectMapper: ObjectMapper
) : CafeMenuQueryRepository {
    override suspend fun findById(id: String): com.breadkun.backend.cafe.menu.model.CafeMenu? {
        return cafeMenuCoroutineCrudRepository.findById(id)
    }

    override suspend fun findByMultipleOptionsWithGrouping(
        cafeLocation: Location?,
        name: String?,
        category: CafeMenuCategory?,
        page: Int?,
        size: Int?
    ): List<CafeMenuBoardDTO> {
        val baseQuery = """
            WITH grouped_data AS (
                SELECT cafe_location, name, category,
                    json_agg(
                        json_build_object(
                            'drinkTemperature', drink_temperature,
                            'id', id,
                            'available', available,
                            'price', price,
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
                GROUP BY cafe_location, category, name
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
            CafeMenuBoardDTO(
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