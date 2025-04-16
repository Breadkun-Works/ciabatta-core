package com.ciabatta.core.cafe.application.service

import com.ciabatta.core.cafe.domain.model.CafeCartItem
import com.ciabatta.core.global.enums.GlobalEnums
import com.ciabatta.core.global.sse.SseEventTopic
import com.ciabatta.core.global.sse.SseService
import com.ciabatta.core.global.util.ResponseUtils
import org.springframework.stereotype.Component

@Component
class CafeCartItemSseEventPublisher(
    private val sseService: SseService,
) {
    suspend fun publishCreated(domains: List<CafeCartItem>): Unit =
        sseService.publish(
            topic = SseEventTopic.cafeCartItem(domains.first().cafeCartId),
            data = ResponseUtils.sse(GlobalEnums.EventType.CREATED, domains, "cafeCartItem"),
        )

    suspend fun publishDeleted(
        cafeCartId: String,
        cafeCartItemIds: List<String>,
    ): Unit =
        sseService.publish(
            topic = SseEventTopic.cafeCartItem(cafeCartId),
            data = ResponseUtils.sse(GlobalEnums.EventType.DELETED, cafeCartItemIds, "id"),
        )
}
