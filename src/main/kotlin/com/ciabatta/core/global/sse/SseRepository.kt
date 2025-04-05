package com.ciabatta.core.global.sse

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.springframework.http.codec.ServerSentEvent
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class SseRepository {
    fun subscribe(
        topic: String
    ) = getOrCreateFlow(topic).asSharedFlow()

    suspend fun publish(
        topic: String,
        data: Any
    ): Unit = getOrCreateFlow(topic).emit(
        ServerSentEvent.builder<Any>()
            .event(topic)
            .data(data)
            .build()
    )

    private val flows = ConcurrentHashMap<String, MutableSharedFlow<ServerSentEvent<Any>>>()

    private fun getOrCreateFlow(
        topic: String
    ): MutableSharedFlow<ServerSentEvent<Any>> = flows.computeIfAbsent(topic) {
        MutableSharedFlow(
            replay = 0, // 연결 이후의 이벤트 전송
            extraBufferCapacity = 100, // 처리 속도 < 이벤트 발생 => 버퍼 크기 100개
            onBufferOverflow = BufferOverflow.DROP_OLDEST // 버퍼 full 시 오래된 것 부터 제거
        )
    }
}
