package com.ciabatta.core.global.sse

import com.ciabatta.core.global.exception.ErrorCode
import com.ciabatta.core.global.exception.SseException
import kotlinx.coroutines.flow.Flow
import org.springframework.http.codec.ServerSentEvent
import org.springframework.stereotype.Service

@Service
class SseService(
    private val sseRepository: SseRepository
) {
    /**
     * 구독 중인 모든 클라이언트에게 이벤트를 전송.
     * @param topic - 구독 대상 토픽
     * @param data - 전달할 데이터 (SseResponse 등)
     */
    suspend fun publish(
        topic: String,
        data: Any
    ): Unit = try {
        sseRepository.publish(topic, data)
    } catch (e: Exception) {
        throw SseException(
            error = ErrorCode.SSE_1001,
            message = "Failed to publish SSE event to topic: $topic"
        )
    }

    /**
     * SSE 구독 생성. 클라이언트는 이 스트림을 통해 실시간 데이터를 수신.
     */
    fun subscribe(
        topic: String
    ): Flow<ServerSentEvent<Any>> = try {
        sseRepository.subscribe(topic)
    } catch (e: Exception) {
        throw SseException(
            error = ErrorCode.SSE_1002,
            message = "Failed to subscribe to SSE topic: $topic"
        )
    }
}