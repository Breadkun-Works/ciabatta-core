package com.ciabatta.core.cafe.infrastructure.web.handler

import com.ciabatta.core.cafe.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.global.util.HeaderUtils
import com.ciabatta.core.global.util.ResponseUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class CafeCartQueryHandler(
    private val cafeCartQueryUseCase: CafeCartQueryUseCase,
    @Value("\${secure.ssr-secret-key}") private val ssrSecretKey: String,
) {
    suspend fun getCafeCartById(request: ServerRequest): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")
        val includeSecureKey = HeaderUtils.getOptionalHeader("X-SSR-Token", request) == ssrSecretKey

        val result = cafeCartQueryUseCase.getCafeCartById(cafeCartId, includeSecureKey)

        return ResponseUtils.ok(result, "cafeCart")
    }
}
