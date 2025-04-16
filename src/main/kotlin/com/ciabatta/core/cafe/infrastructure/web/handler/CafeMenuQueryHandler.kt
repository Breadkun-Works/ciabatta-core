package com.ciabatta.core.cafe.infrastructure.web.handler

import com.ciabatta.core.cafe.application.port.input.CafeMenuQueryUseCase
import com.ciabatta.core.cafe.domain.model.enums.CafeEnums
import com.ciabatta.core.global.enums.GlobalEnums
import com.ciabatta.core.global.util.PaginationUtils
import com.ciabatta.core.global.util.ResponseUtils
import kotlin.jvm.optionals.getOrNull
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class CafeMenuQueryHandler(
    private val cafeMenuQueryUseCase: CafeMenuQueryUseCase,
) {
    suspend fun getCafeMenuBoardByOptions(request: ServerRequest): ServerResponse {
        val cafeLocation =
            request.queryParam("cafeLocation").getOrNull()?.takeIf { it.isNotBlank() }
                ?.let { GlobalEnums.Location.valueOf(it) }
        val name = request.queryParam("name").getOrNull()?.trim()
        val category =
            request.queryParam("category").getOrNull()?.takeIf { it.isNotBlank() }
                ?.let { CafeEnums.Menu.Category.valueOf(it) }
        val page = request.queryParam("page").getOrNull()?.takeIf { it.isNotBlank() }?.toInt()
        val size = request.queryParam("size").getOrNull()?.takeIf { it.isNotBlank() }?.toInt()

        val pageable = PaginationUtils.validatePagination(page, size)

        val result = cafeMenuQueryUseCase.getCafeMenuBoardByOptions(cafeLocation, name, category, pageable)

        return if (pageable.isPaged) {
            ResponseUtils.ok(result, "cafeMenuBoard")
        } else {
            ResponseUtils.ok(result.content, "cafeMenuBoard")
        }
    }
}
