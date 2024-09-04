package com.breadkun.backend.global.common.util

//import org.springframework.data.domain.PageImpl
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

object ResponseUtils {
    suspend fun ok(): ServerResponse {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .buildAndAwait()
    }

    suspend fun <T : Any> ok(body: T): ServerResponse {
        return ServerResponse
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(body)
    }

//    suspend fun <T : Flow<Any>> ok(body: T): ServerResponse {
//        return ServerResponse
//            .ok()
//            .contentType(MediaType.APPLICATION_JSON)
//            .bodyAndAwait(body)
//    }

//    suspend fun <T> ok(pageable: PageImpl<T>): ServerResponse {
//        return ServerResponse
//            .ok()
//            .contentType(MediaType.APPLICATION_JSON)
//            .header("Total-Elements", pageable.totalElements.toString())
//            .header("Total-Pages", pageable.totalPages.toString())
//            .header("Page-Size", pageable.size.toString())
//            .header("Current-Page", pageable.number.toString())
//            .bodyValueAndAwait(pageable.content)
//    }
}