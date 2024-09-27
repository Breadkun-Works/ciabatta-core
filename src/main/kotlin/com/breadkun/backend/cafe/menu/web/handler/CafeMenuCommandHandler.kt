package com.breadkun.backend.cafe.menu.web.handler

import com.breadkun.backend.domain.cafe.menu.dto.request.CafeMenuCreateDTO
import com.breadkun.backend.cafe.menu.dto.request.CafeMenuUpdateDTO
import com.breadkun.backend.cafe.menu.service.CafeMenuCommandService
import com.breadkun.backend.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class AdminCafeMenuCommandHandler(
    private val cafeMenuCommandService: com.breadkun.backend.cafe.menu.service.CafeMenuCommandService
) {
    suspend fun createCafeMenu(request: ServerRequest): ServerResponse {
        val userID = request.headers().firstHeader("X-User-ID")
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Missing X-User-ID header")
        val cafeMenuCreateDTO = request.awaitBody<CafeMenuCreateDTO>()

        val createdMenu = cafeMenuCommandService.createCafeMenu(userID, cafeMenuCreateDTO)

        return ResponseUtils.ok(createdMenu)
    }

    suspend fun updateCafeMenu(request: ServerRequest): ServerResponse {
        val cafeMenuId = request.pathVariable("cafeMenuId")
        val userID = request.headers().firstHeader("X-User-ID")
            ?: return ServerResponse.badRequest().bodyValueAndAwait("Missing X-User-ID header")
        val cafeMenuUpdateDTO = request.awaitBody<com.breadkun.backend.cafe.menu.dto.request.CafeMenuUpdateDTO>()

        val updatedMenu = cafeMenuCommandService.updateCafeMenu(cafeMenuId, userID, cafeMenuUpdateDTO)

        return updatedMenu?.let { ResponseUtils.ok(it) } ?: ServerResponse.notFound().buildAndAwait()
    }

    suspend fun deleteCafeMenuById(request: ServerRequest): ServerResponse {
        val cafeMenuId = request.pathVariable("cafeMenuId")
        val deletedId = cafeMenuCommandService.deleteCafeMenuById(cafeMenuId)

        return deletedId?.let { ResponseUtils.ok(it) } ?: ServerResponse.notFound().buildAndAwait()
    }
}