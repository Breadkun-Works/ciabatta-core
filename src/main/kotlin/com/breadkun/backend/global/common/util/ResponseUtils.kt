package com.breadkun.backend.global.common.util

import com.breadkun.backend.global.common.model.ApiResponse
import com.breadkun.backend.global.common.model.MetaData
import org.springframework.data.domain.PageImpl
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

object ResponseUtils {
    suspend fun <T> ok(data: T): ServerResponse {
        val meta = MetaData(
            totalItems = if (data is Collection<*>) data.size else 1
        )
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(ApiResponse(success = true, meta = meta, data = data))
    }

    suspend fun <T> ok(pageable: PageImpl<T>): ServerResponse {
        val meta = MetaData(
            totalItems = pageable.totalElements.toInt(),
            totalPages = pageable.totalPages,
            pageSize = pageable.size,
            currentPage = pageable.number
        )
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(ApiResponse(success = true, meta = meta, data = pageable.content))
    }
}