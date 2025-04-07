package com.ciabatta.core.infrastructure.web.handler

import com.ciabatta.core.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.application.validator.CafeCartValidator
import com.ciabatta.core.global.sse.SseEventTopic
import com.ciabatta.core.global.sse.SseService
import com.ciabatta.core.global.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class CafeCartItemSseHandler(
    private val sseService: SseService,
    private val cafeCartValidator: CafeCartValidator,
    private val cafeCartQueryUseCase: CafeCartQueryUseCase
) {
    suspend fun subscribeCafeCartItem(
        request: ServerRequest
    ): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")

        val cafeCart = cafeCartQueryUseCase.getCafeCartById(cafeCartId, false)
        cafeCartValidator.assertCartIsActive(cafeCart)

        val topic = SseEventTopic.cafeCartItem(cafeCartId)
        val stream = sseService.subscribe(topic)

        return ResponseUtils.sseHandshake(stream)
    }
}