package com.ciabatta.core.global.util

import com.ciabatta.core.global.model.ApiResponse
import com.ciabatta.core.global.model.MetaData
import org.springframework.data.domain.PageImpl
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

object ResponseUtils {
    suspend fun <T> ok(data: T?, dataKey: String): ServerResponse {
        val meta = MetaData(totalItems = if (data == null) 0 else if (data is Collection<*>) data.size else 1)

        val wrappedData = wrapData(data, dataKey)

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(ApiResponse(success = true, meta = meta, data = wrappedData))
    }

    suspend fun <T> ok(pageable: PageImpl<T>, dataKey: String): ServerResponse {
        val meta = MetaData(
            totalItems = pageable.totalElements.toInt(),
            totalPages = pageable.totalPages,
            pageSize = pageable.size,
            currentPage = pageable.number
        )

        val wrappedData = if (pageable.content.isEmpty()) null else mapOf(dataKey to pageable.content)

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(ApiResponse(success = true, meta = meta, data = wrappedData))
    }


    suspend fun <T> created(data: T?, dataKey: String): ServerResponse {
        val meta = MetaData(totalItems = if (data == null) 0 else if (data is Collection<*>) data.size else 1)

        val wrappedData = wrapData(data, dataKey)

        return ServerResponse
            .status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(ApiResponse(success = true, meta = meta, data = wrappedData))
    }

    suspend fun noContent(): ServerResponse {
        return ServerResponse
            .noContent()
            .buildAndAwait()
    }

    private fun <T> wrapData(data: T?, dataKey: String): Map<String, Any>? {
        return if (data == null || (data is Collection<*> && data.isEmpty())) null else mapOf(dataKey to data)
    }
}