package com.ciabatta.core.infrastructure.web.handler

import com.ciabatta.core.application.dto.CafeMenuCreateDTO
import com.ciabatta.core.application.dto.CafeMenuUpdateDTO
import com.ciabatta.core.application.port.input.CafeMenuCommandUseCase
import com.ciabatta.core.global.exception.ErrorCode
import com.ciabatta.core.global.exception.ValidationException
import com.ciabatta.core.global.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class AdminCafeMenuCommandHandler(
    private val cafeMenuCommandUseCase: CafeMenuCommandUseCase
) {
    suspend fun createCafeMenu(
        request: ServerRequest
    ): ServerResponse {
        val userID = request.headers().firstHeader("X-User-ID")?.trim()?.takeIf { it.isNotBlank() }
            ?: throw ValidationException(ErrorCode.VAL_0001, "Missing X-User-ID header")
        val cafeMenuCreateDTO = request.awaitBody<CafeMenuCreateDTO>()

        val createdMenu = cafeMenuCommandUseCase.createCafeMenu(userID, cafeMenuCreateDTO)

        return ResponseUtils.created(createdMenu, "cafeMenu")
    }

    suspend fun updateCafeMenu(
        request: ServerRequest
    ): ServerResponse {
        val cafeMenuId = request.pathVariable("cafeMenuId").toLong()
        val userID = request.headers().firstHeader("X-User-ID")?.trim()?.takeIf { it.isNotBlank() }
            ?: throw ValidationException(ErrorCode.VAL_0001, "Missing X-User-ID header")
        val cafeMenuUpdateDTO = request.awaitBody<CafeMenuUpdateDTO>()

        val updatedMenu = cafeMenuCommandUseCase.updateCafeMenu(cafeMenuId, userID, cafeMenuUpdateDTO)

        return ResponseUtils.ok(updatedMenu, "cafeMenu")
    }

    suspend fun deleteCafeMenuById(
        request: ServerRequest
    ): ServerResponse {
        val cafeMenuId = request.pathVariable("cafeMenuId").toLong()

        cafeMenuCommandUseCase.deleteCafeMenuById(cafeMenuId)

        return ResponseUtils.noContent()
    }
}