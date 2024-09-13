package com.breadkun.backend.domain.cafe.web.handler

import com.breadkun.backend.domain.cafe.service.CafeCartQueryService
import com.breadkun.backend.global.common.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class CafeCartQueryHandler(
    private val cafeCartQueryService: CafeCartQueryService
) {
    suspend fun findActiveCafeCartByCreatedById(request: ServerRequest): ServerResponse {
        val createdById = request.queryParam("createdById")
            .orElseThrow { IllegalArgumentException("Missing 'createdById' query parameter") }

        val result = cafeCartQueryService.findActiveCafeCartByCreatedById(createdById)

        return ResponseUtils.ok(result)
    }
}