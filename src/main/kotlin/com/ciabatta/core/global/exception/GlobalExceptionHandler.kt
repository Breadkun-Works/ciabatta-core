package com.ciabatta.core.global.exception

import com.ciabatta.core.global.util.ResponseUtils
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebExceptionHandler
import reactor.core.publisher.Mono

@Component
@Order(-2)
class GlobalErrorWebExceptionHandler : WebExceptionHandler {
    override fun handle(
        exchange: ServerWebExchange,
        ex: Throwable
    ): Mono<Void> = mono {
        val (httpStatus, errorCode) = when (ex) {
            is ValidationException -> ex.status to ex.errorCode
            is BusinessException -> ex.status to ex.errorCode
            is IllegalArgumentException,
            is WebExchangeBindException -> HttpStatus.BAD_REQUEST to "E-400"

            else -> HttpStatus.INTERNAL_SERVER_ERROR to "E-500"
        }

        val errorMessage = ex.message ?: "알 수 없는 오류가 발생했습니다."

        val errorResponse = ResponseUtils.error(
            code = httpStatus,
            errorCode = errorCode,
            errorMessage = errorMessage
        )

        errorResponse.writeTo(exchange, object : ServerResponse.Context {
            override fun messageWriters() = HandlerStrategies.withDefaults().messageWriters()
            override fun viewResolvers() = HandlerStrategies.withDefaults().viewResolvers()
        }).awaitSingleOrNull()
    }
}