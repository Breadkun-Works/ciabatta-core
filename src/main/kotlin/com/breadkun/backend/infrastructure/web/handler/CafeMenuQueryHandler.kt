package com.breadkun.backend.infrastructure.web.handler

import com.breadkun.backend.global.common.enums.Location
import com.breadkun.backend.domain.model.enums.CafeMenuCategory
import com.breadkun.backend.global.common.util.PaginationUtils
import com.breadkun.backend.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import kotlin.jvm.optionals.getOrNull

@Component
class CafeMenuQueryHandler(
    private val cafeMenuQueryUseCase: com.breadkun.backend.application.port.input.CafeMenuQueryUseCase
) {
    suspend fun getCafeMenuBoardByOptions(request: ServerRequest): ServerResponse {
        val cafeLocation =
            request.queryParam("cafeLocation").getOrNull()?.takeIf { it.isNotBlank() }?.let { Location.valueOf(it) }
        val name = request.queryParam("name").getOrNull()?.trim()
        val category =
            request.queryParam("category").getOrNull()?.takeIf { it.isNotBlank() }?.let { CafeMenuCategory.valueOf(it) }
        val page = request.queryParam("page").getOrNull()?.takeIf { it.isNotBlank() }?.toInt()
        val size = request.queryParam("size").getOrNull()?.takeIf { it.isNotBlank() }?.toInt()

        val pageable = PaginationUtils.validatePagination(page, size)

        val result = cafeMenuQueryUseCase.getCafeMenuBoardByOptions(cafeLocation, name, category, pageable)

        return if (pageable.isPaged) {
            ResponseUtils.ok(result)
        } else {
            ResponseUtils.ok(result.content)
        }
    }
}