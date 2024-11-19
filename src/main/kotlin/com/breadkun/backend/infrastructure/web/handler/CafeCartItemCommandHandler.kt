package com.breadkun.backend.infrastructure.web.handler

import com.breadkun.backend.application.dto.CafeCartItemCreateDTO
import com.breadkun.backend.application.port.input.CafeCartItemCommandUseCase
import com.breadkun.backend.global.common.dto.DeleteIdsDTO
import com.breadkun.backend.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class CafeCartItemCommandHandler(
    private val cafeCartItemCommandUseCase: CafeCartItemCommandUseCase
) {
    suspend fun createCafeCartItems(
        request: ServerRequest
    ): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")
        val userUUID = request.headers().firstHeader("X-User-UUID")
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Missing X-User-UUID header")
        val userName = request.headers().firstHeader("X-User-Name")
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Missing X-User-Name header")

        val cafeCartItemCreateDTOs = request.awaitBody<List<CafeCartItemCreateDTO>>()

        val createdCartItems =
            cafeCartItemCommandUseCase.createCafeCartItems(cafeCartId, userUUID, userName, cafeCartItemCreateDTOs)

        return ResponseUtils.ok(createdCartItems, "cafeCartItem")
    }

    suspend fun deleteCafeCartItems(
        request: ServerRequest
    ): ServerResponse {
        val dto = request.awaitBody<DeleteIdsDTO>()

        cafeCartItemCommandUseCase.deleteCafeCartItems(dto)

        return ResponseUtils.noContent()
    }
}