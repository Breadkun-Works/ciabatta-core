package com.breadkun.backend.domain.cafe.web.handler

import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import com.breadkun.backend.domain.cafe.model.enum.CafeMenuCategory
import com.breadkun.backend.domain.cafe.service.CafeMenuQueryService
import com.breadkun.backend.global.common.util.PaginationUtils
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
        val cafeLocation =
            request.queryParam("cafeLocation").getOrNull()?.takeIf { it.isNotBlank() }?.let { CafeLocation.valueOf(it) }
        val name = request.queryParam("name").getOrNull()?.trim()
        val category =
            request.queryParam("category").getOrNull()?.takeIf { it.isNotBlank() }?.let { CafeMenuCategory.valueOf(it) }
        val page = request.queryParam("page").getOrNull()?.takeIf { it.isNotBlank() }?.toInt()
        val size = request.queryParam("size").getOrNull()?.takeIf { it.isNotBlank() }?.toInt()

        val pageable = PaginationUtils.validatePagination(page, size)

        val result = cafeMenuQueryService.getCafeMenuBoardByOptions(cafeLocation, name, category, pageable)

        return if (pageable.isPaged) {
            ResponseUtils.ok(result)
        } else {
            ResponseUtils.ok(result.content)
        }
    }
}