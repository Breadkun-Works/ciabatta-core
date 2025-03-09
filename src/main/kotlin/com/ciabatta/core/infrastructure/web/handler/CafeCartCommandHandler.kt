package com.ciabatta.core.infrastructure.web.handler

import com.ciabatta.core.application.dto.CafeCartCreateDTO
import com.ciabatta.core.application.port.input.CafeCartCommandUseCase
import com.ciabatta.core.global.dto.DeleteIdsDTO
import com.ciabatta.core.global.exception.ErrorCode
import com.ciabatta.core.global.exception.ValidationException
import com.ciabatta.core.global.util.ResponseUtils
import com.ciabatta.core.global.util.awaitValidatedBody
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.validation.Validator

@Component
class CafeCartCommandHandler(
    private val cafeCartCommandUseCase: CafeCartCommandUseCase,
    private val validator: Validator
) {
    suspend fun createCafeCart(
        request: ServerRequest
    ): ServerResponse {
        val userUUID = request.headers().firstHeader("X-User-UUID")?.trim()?.takeIf { it.isNotBlank() }
            ?: throw ValidationException(ErrorCode.VAL_0001, "Missing X-User-UUID header")
        val cafeCartCreateDTO = request.awaitValidatedBody<CafeCartCreateDTO>(validator)

        val createdCart = cafeCartCommandUseCase.createCafeCart(userUUID, cafeCartCreateDTO)

        return ResponseUtils.created(createdCart, "cafeCart")
    }

    suspend fun deleteCafeCarts(
        request: ServerRequest
    ): ServerResponse {
        val dto = request.awaitValidatedBody<DeleteIdsDTO>(validator)

        cafeCartCommandUseCase.deleteCafeCarts(dto)

        return ResponseUtils.noContent()
    }
}