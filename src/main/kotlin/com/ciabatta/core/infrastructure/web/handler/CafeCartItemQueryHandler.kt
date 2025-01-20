package com.ciabatta.core.infrastructure.web.handler

import com.ciabatta.core.application.port.input.CafeCartItemQueryUseCase
import com.ciabatta.core.global.common.enums.GlobalEnums
import com.ciabatta.core.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import kotlin.jvm.optionals.getOrNull

@Component
class CafeCartItemQueryHandler(
    private val cafeCartItemQueryUseCase: CafeCartItemQueryUseCase
) {
    suspend fun findCafeCartItemsByCafeCartId(
        request: ServerRequest
    ): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")
        val include = request.queryParam("include").getOrNull()?.takeIf { it.isNotBlank() }
            ?.let { GlobalEnums.IncludeOption.valueOf(it) }

        val result = cafeCartItemQueryUseCase.findCafeCartItemsByCafeCartId(cafeCartId, include)

        return ResponseUtils.ok(result, "cafeCartItem")
    }

    suspend fun findCafeCartItemSummaryByCafeCartId(
        request: ServerRequest
    ): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")

        val result = cafeCartItemQueryUseCase.findCafeCartItemSummaryByCafeCartId(cafeCartId)

        return ResponseUtils.ok(result, "cafeCartItemSummary")
    }
}