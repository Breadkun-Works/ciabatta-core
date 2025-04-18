package com.ciabatta.core.cafe.infrastructure.web.handler

import com.ciabatta.core.cafe.application.dto.CafeCartCreateDTO
import com.ciabatta.core.cafe.application.port.input.CafeCartCommandUseCase
import com.ciabatta.core.global.util.HeaderUtils
import com.ciabatta.core.global.util.ResponseUtils
import com.ciabatta.core.global.util.awaitValidatedBody
import org.springframework.stereotype.Component
import org.springframework.validation.Validator
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class CafeCartCommandHandler(
    private val cafeCartCommandUseCase: CafeCartCommandUseCase,
    private val validator: Validator,
) {
    suspend fun createCafeCart(request: ServerRequest): ServerResponse {
        val userUUID = HeaderUtils.getHeader("X-User-UUID", request)

        val cafeCartCreateDTO = request.awaitValidatedBody<CafeCartCreateDTO>(validator)

        val createdCart = cafeCartCommandUseCase.createCafeCart(userUUID, cafeCartCreateDTO)

        return ResponseUtils.created(createdCart, "cafeCart")
    }

    suspend fun expireCafeCart(request: ServerRequest): ServerResponse {
        val userUUID = HeaderUtils.getHeader("X-User-UUID", request)

        val cafeCartId = request.pathVariable("cafeCartId")

        cafeCartCommandUseCase.expireCafeCart(userUUID, cafeCartId)

        return ResponseUtils.noContent()
    }
}
