package com.ciabatta.core.global.sse

import com.ciabatta.core.global.exception.ErrorCode
import com.ciabatta.core.global.exception.SseException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.timeout
import org.springframework.http.codec.ServerSentEvent
import org.springframework.stereotype.Service
import kotlin.time.Duration.Companion.minutes

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
    ): Flow<ServerSentEvent<Any>> = sseRepository.subscribe(topic)
        .timeout(20.minutes) // 20분 동안 이벤트가 없으면 타임아웃
        .catch { e ->
            when(e){
                is TimeoutCancellationException -> {
                    throw SseException(
                        error = ErrorCode.SSE_1003,
                        message = "SSE connection timed out due to inactivity"
                    )
                }
                else -> {
                    throw SseException(
                        error = ErrorCode.SSE_1002,
                        message = "Failed to subscribe to SSE topic: $topic"
                    )
                }
            }
        }
}