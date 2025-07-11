package com.ciabatta.core.cafe.infrastructure.web.handler

import com.ciabatta.core.cafe.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.cafe.application.validator.CafeValidator
import com.ciabatta.core.global.sse.SseEventTopic
import com.ciabatta.core.global.sse.SseService
import com.ciabatta.core.global.util.ResponseUtils
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class CafeCartItemSseHandler(
    private val sseService: SseService,
    private val cafeValidator: CafeValidator,
    private val cafeCartQueryUseCase: CafeCartQueryUseCase,
) {
    suspend fun subscribeCafeCartItem(request: ServerRequest): ServerResponse {
        val cafeCartId = request.pathVariable("cafeCartId")

        val cafeCart = cafeCartQueryUseCase.getCafeCartById(cafeCartId, false)
        cafeValidator.assertCartIsActive(cafeCart) // 도메인 전용 SSE 핸들러는 자체 비즈니스 로직이 없기 때문에 handler에서 검증 로직 수행

        val topic = SseEventTopic.cafeCartItem(cafeCartId)
        val stream = sseService.subscribe(topic)

        return ResponseUtils.sseHandshake(stream)
    }
}
