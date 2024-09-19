package com.breadkun.backend.domain.cafe.web.handler

import com.breadkun.backend.domain.cafe.dto.request.CafeCartItemCreateDTO
import com.breadkun.backend.domain.cafe.service.CafeCartItemCommandService
import com.breadkun.backend.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody

@Component
class CafeCartItemCommandHandler(
    private val cafeCartItemCommandService: CafeCartItemCommandService
) {
    suspend fun createCafeCartItems(request: ServerRequest): ServerResponse {
        val cafeCartItemCreateDTOs = request.awaitBody<List<CafeCartItemCreateDTO> >()
        val createdCartItems = cafeCartItemCommandService.createCafeCartItems(cafeCartItemCreateDTOs)

        return ResponseUtils.ok(createdCartItems)
    }
}