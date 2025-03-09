package com.ciabatta.core.infrastructure.web.handler

import com.ciabatta.core.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.enums.GlobalEnums
import com.ciabatta.core.global.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import kotlin.jvm.optionals.getOrNull

@Component
class CafeCartQueryHandler(
    private val cafeCartQueryUseCase: CafeCartQueryUseCase
) {
    suspend fun findCafeCartsByOptions(
        request: ServerRequest
    ): ServerResponse {
        val cafeLocation =
            request.queryParam("cafeLocation").getOrNull()?.takeIf { it.isNotBlank() }
                ?.let { GlobalEnums.Location.valueOf(it) }
        val status = request.queryParam("status").getOrNull()?.takeIf { it.isNotBlank() }
            ?.let { CafeEnums.Cart.Status.valueOf(it) }
        val createdById = request.queryParam("createdById").getOrNull()?.takeIf { it.isNotBlank() }

        val result = cafeCartQueryUseCase.findCafeCartsByOptions(cafeLocation, status, createdById)

        return ResponseUtils.ok(result, "cafeCart")
    }

    suspend fun findCafeCartById(
        request: ServerRequest
    ): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")

        val result = cafeCartQueryUseCase.findCafeCartById(cafeCartId)

        return ResponseUtils.ok(result, "cafeCart")
    }
}