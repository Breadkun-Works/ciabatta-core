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
    ) = getOrCreateFlow(topic).emit(
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
            replay = 0,
            extraBufferCapacity = 100,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    }
}
