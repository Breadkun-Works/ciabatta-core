package com.ciabatta.core.cafe.infrastructure.web.handler

import com.ciabatta.core.cafe.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.cafe.application.port.input.CafeCartItemCommandUseCase
import com.ciabatta.core.global.dto.DeleteIdsDTO
import com.ciabatta.core.global.util.HeaderUtils
import com.ciabatta.core.global.util.ResponseUtils
import com.ciabatta.core.global.util.awaitValidatedBody
import com.ciabatta.core.global.util.awaitValidatedBodyList
import org.springframework.stereotype.Component
import org.springframework.validation.Validator
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class CafeCartItemCommandHandler(
    private val cafeCartItemCommandUseCase: CafeCartItemCommandUseCase,
    private val validator: Validator,
) {
    suspend fun createCafeCartItems(request: ServerRequest): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")

        val userUUID = HeaderUtils.getHeader("X-User-UUID", request)
        val userName = HeaderUtils.getBase64Header("X-User-Name", request)

        val dtos = request.awaitValidatedBodyList<CafeCartItemCreateDTO>(validator)

        val createdCartItems = cafeCartItemCommandUseCase.createCafeCartItems(cafeCartId, userUUID, userName, dtos)

        return ResponseUtils.created(createdCartItems, "cafeCartItem")
    }

    suspend fun deleteCafeCartItems(request: ServerRequest): ServerResponse {
        val userUUID = HeaderUtils.getHeader("X-User-UUID", request)

        val dto = request.awaitValidatedBody<DeleteIdsDTO>(validator)

        cafeCartItemCommandUseCase.deleteCafeCartItems(userUUID, dto)

        return ResponseUtils.noContent()
    }
}
