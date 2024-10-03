package com.breadkun.backend.cafe.infrastructure.web.handler

import com.breadkun.backend.cafe.application.dto.request.CafeMenuCreateDTO
import com.breadkun.backend.cafe.application.dto.request.CafeMenuUpdateDTO
import com.breadkun.backend.cafe.application.port.input.CafeMenuCommandUseCase
import com.breadkun.backend.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class AdminCafeMenuCommandHandler(
    private val cafeMenuCommandUseCase: CafeMenuCommandUseCase
) {
    suspend fun createCafeMenu(request: ServerRequest): ServerResponse {
        val userID = request.headers().firstHeader("X-User-ID")
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Missing X-User-ID header")
        val cafeMenuCreateDTO = request.awaitBody<CafeMenuCreateDTO>()

        val createdMenu = cafeMenuCommandUseCase.createCafeMenu(userID, cafeMenuCreateDTO)

        return ResponseUtils.ok(createdMenu)
    }

    suspend fun updateCafeMenu(request: ServerRequest): ServerResponse {
        val cafeMenuId = request.pathVariable("cafeMenuId")
        val userID = request.headers().firstHeader("X-User-ID")
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Missing X-User-ID header")
        val cafeMenuUpdateDTO = request.awaitBody<CafeMenuUpdateDTO>()

        val updatedMenu = cafeMenuCommandUseCase.updateCafeMenu(cafeMenuId, userID, cafeMenuUpdateDTO)

        return updatedMenu?.let { ResponseUtils.ok(it) } ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun deleteCafeMenuById(request: ServerRequest): ServerResponse {
        val cafeMenuId = request.pathVariable("cafeMenuId")
        val deletedId = cafeMenuCommandUseCase.deleteCafeMenuById(cafeMenuId)

        return deletedId?.let { ResponseUtils.ok(it) } ?: ServerResponse.notFound().buildAndAwait()
    }
}