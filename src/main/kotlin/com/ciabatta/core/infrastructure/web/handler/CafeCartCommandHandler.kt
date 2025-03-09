package com.ciabatta.core.infrastructure.web.handler

import com.ciabatta.core.application.dto.CafeCartCreateDTO
import com.ciabatta.core.application.port.input.CafeCartCommandUseCase
import com.ciabatta.core.global.dto.DeleteIdsDTO
import com.ciabatta.core.global.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class CafeCartCommandHandler(
    private val cafeCartCommandUseCase: CafeCartCommandUseCase
) {
    suspend fun createCafeCart(
        request: ServerRequest
    ): ServerResponse {
        val userUUID = request.headers().firstHeader("X-User-UUID")
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Missing X-User-UUID header")
        val cafeCartCreateDTO = request.awaitBody<CafeCartCreateDTO>()
        val createdCart = cafeCartCommandUseCase.createCafeCart(userUUID, cafeCartCreateDTO)

        return ResponseUtils.created(createdCart, "cafeCart")
    }

    suspend fun deleteCafeCarts(
        request: ServerRequest
    ): ServerResponse {
        val dto = request.awaitBody<DeleteIdsDTO>()

        cafeCartCommandUseCase.deleteCafeCarts(dto)

        return ResponseUtils.noContent()
    }
}