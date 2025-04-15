package com.ciabatta.core.global.model

import com.ciabatta.core.global.enums.GlobalEnums
import java.time.LocalDateTime

data class SseResponse<T>(
    val event: GlobalEnums.EventType,
    val meta: SseMetaData,
    val data: T,
)

data class SseMetaData(
    val totalItems: Int,
    val timestamp: LocalDateTime = LocalDateTime.now(),
)
