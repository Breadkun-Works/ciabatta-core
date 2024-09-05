package com.breadkun.backend.domain.cafe.web.handler

import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import com.breadkun.backend.domain.cafe.model.enum.CafeMenuCategory
import com.breadkun.backend.domain.cafe.service.CafeMenuQueryService
import com.breadkun.backend.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import kotlin.jvm.optionals.getOrNull

@Component
class CafeMenuQueryHandler(
    private val cafeMenuQueryService: CafeMenuQueryService
) {
    suspend fun getCafeMenuBoardByOptions(request: ServerRequest): ServerResponse {
        val cafeLocation = request.queryParam("cafeLocation").getOrNull()?.let { CafeLocation.valueOf(it) }
        val name = request.queryParam("name").getOrNull()
        val category = request.queryParam("category").getOrNull()?.let { CafeMenuCategory.valueOf(it) }
        val page = request.queryParam("page").getOrNull()?.toInt()
        val size = request.queryParam("size").getOrNull()?.toInt()

        val result = cafeMenuQueryService.getCafeMenuBoardByOptions(cafeLocation, name, category, page, size)

        return ResponseUtils.ok(result)
    }
}