package com.ciabatta.core.global.util

import com.ciabatta.core.global.enums.GlobalEnums
import com.ciabatta.core.global.model.ApiResponse
import com.ciabatta.core.global.model.ErrorResponse
import com.ciabatta.core.global.model.MetaData
import com.ciabatta.core.global.model.SseMetaData
import com.ciabatta.core.global.model.SseResponse
import kotlinx.coroutines.flow.Flow
import org.springframework.data.domain.PageImpl
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

object ResponseUtils {
    suspend fun <T> ok(
        data: T?,
        dataKey: String,
    ): ServerResponse {
        val meta =
            MetaData(
                totalItems =
                    if (data == null) {
                        0
                    } else if (data is Collection<*>) {
                        data.size
                    } else {
                        1
                    },
            )

        val wrappedData = wrapData(data, dataKey)

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(ApiResponse(success = true, meta = meta, data = wrappedData))
    }

    suspend fun <T> ok(
        pageable: PageImpl<T>,
        dataKey: String,
    ): ServerResponse {
        val meta =
            MetaData(
                totalItems = pageable.totalElements.toInt(),
                totalPages = pageable.totalPages,
                pageSize = pageable.size,
                currentPage = pageable.number,
            )

        val wrappedData = if (pageable.content.isEmpty()) null else mapOf(dataKey to pageable.content)

        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(ApiResponse(success = true, meta = meta, data = wrappedData))
    }

    suspend fun <T> created(
        data: T?,
        dataKey: String,
    ): ServerResponse {
        val meta =
            MetaData(
                totalItems =
                    if (data == null) {
                        0
                    } else if (data is Collection<*>) {
                        data.size
                    } else {
                        1
                    },
            )

        val wrappedData = wrapData(data, dataKey)

        return ServerResponse
            .status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(ApiResponse(success = true, meta = meta, data = wrappedData))
    }

    suspend fun noContent(): ServerResponse =
        ServerResponse
            .noContent()
            .buildAndAwait()

    suspend fun error(
        code: HttpStatus,
        errorCode: String,
        errorMessage: String,
    ): ServerResponse =
        ServerResponse
            .status(code)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(
                ApiResponse(
                    success = false,
                    // 오류 시에는 메타데이터를 비워두거나 원하는 대로 넣을 수 있음
                    meta = null,
                    data = null,
                    error = ErrorResponse(code = errorCode, message = errorMessage),
                ),
            )

    suspend fun <T : Any> sseHandshake(flow: Flow<T>): ServerResponse =
        ServerResponse
            .ok()
            .contentType(MediaType.TEXT_EVENT_STREAM)
            .bodyAndAwait(flow as Flow<Any>)

    fun <T> sse(
        event: GlobalEnums.EventType,
        data: List<T>,
        dataKey: String,
    ): SseResponse<Map<String, Any>> =
        SseResponse(
            event = event,
            data = wrapData(data, dataKey)!!,
            meta = SseMetaData(data.size),
        )

    private fun <T> wrapData(
        data: T?,
        dataKey: String,
    ): Map<String, Any>? =
        if (data == null || (data is Collection<*> && data.isEmpty())) null else mapOf(dataKey to data)
}
