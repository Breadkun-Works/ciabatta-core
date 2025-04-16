package com.ciabatta.core.cafe.infrastructure.web.handler

import com.ciabatta.core.cafe.application.port.input.CafeCartItemQueryUseCase
import com.ciabatta.core.global.enums.GlobalEnums
import com.ciabatta.core.global.util.ResponseUtils
import kotlin.jvm.optionals.getOrNull
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class CafeCartItemQueryHandler(
    private val cafeCartItemQueryUseCase: CafeCartItemQueryUseCase,
) {
    suspend fun findCafeCartItemsByCafeCartId(request: ServerRequest): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")
        val include =
            request.queryParam("include").getOrNull()?.takeIf { it.isNotBlank() }
                ?.let { GlobalEnums.IncludeOption.valueOf(it) }

        val result = cafeCartItemQueryUseCase.findCafeCartItemsByCafeCartId(cafeCartId, include)

        return ResponseUtils.ok(result, "cafeCartItem")
    }
}
