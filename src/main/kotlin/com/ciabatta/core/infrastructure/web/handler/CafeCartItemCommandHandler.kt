package com.ciabatta.core.infrastructure.web.handler

import com.ciabatta.core.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.application.port.input.CafeCartItemCommandUseCase
import com.ciabatta.core.global.dto.DeleteIdsDTO
import com.ciabatta.core.global.exception.ErrorCode
import com.ciabatta.core.global.exception.ValidationException
import com.ciabatta.core.global.util.ResponseUtils
import com.ciabatta.core.global.util.awaitValidatedBody
import com.ciabatta.core.global.util.awaitValidatedBodyList
import org.springframework.stereotype.Component
import org.springframework.validation.Validator
import org.springframework.web.reactive.function.server.*

@Component
class CafeCartItemCommandHandler(
    private val cafeCartItemCommandUseCase: CafeCartItemCommandUseCase,
    private val validator: Validator
) {
    suspend fun createCafeCartItems(
        request: ServerRequest
    ): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")
        val userUUID = request.headers().firstHeader("X-User-UUID")?.trim()?.takeIf { it.isNotBlank() }
            ?: throw ValidationException(ErrorCode.VAL_0001, "Missing X-User-UUID header")
        val userName = request.headers().firstHeader("X-User-Name")?.trim()?.takeIf { it.isNotBlank() }
            ?: throw ValidationException(ErrorCode.VAL_0001, "Missing X-User-Name header")

        val cafeCartItemCreateDTOs = request.awaitValidatedBodyList<CafeCartItemCreateDTO>(validator)

        val createdCartItems =
            cafeCartItemCommandUseCase.createCafeCartItems(cafeCartId, userUUID, userName, cafeCartItemCreateDTOs)

        return ResponseUtils.created(createdCartItems, "cafeCartItem")
    }

    suspend fun deleteCafeCartItems(
        request: ServerRequest
    ): ServerResponse {
        val dto = request.awaitValidatedBody<DeleteIdsDTO>(validator)

        cafeCartItemCommandUseCase.deleteCafeCartItems(dto)

        return ResponseUtils.noContent()
    }
}