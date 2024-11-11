package com.breadkun.backend.infrastructure.web.handler

import com.breadkun.backend.application.port.input.CafeCartQueryUseCase
import com.breadkun.backend.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import kotlin.jvm.optionals.getOrNull

@Component
class CafeCartQueryHandler(
    private val cafeCartQueryUseCase: CafeCartQueryUseCase
) {
    suspend fun findActiveCafeCartById(request: ServerRequest): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")

        val result = cafeCartQueryUseCase.findActiveCafeCartById(cafeCartId)

        return ResponseUtils.ok(result)
    }
    suspend fun findActiveCafeCartsByOptions(request: ServerRequest): ServerResponse {
        val createdById = request.queryParam("createdById").getOrNull()?.takeIf { it.isNotBlank() }

        val result = cafeCartQueryUseCase.findActiveCafeCartsByOptions(createdById)

        return ResponseUtils.ok(result)
    }
}