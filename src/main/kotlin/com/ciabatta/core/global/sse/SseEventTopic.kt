package com.ciabatta.core.global.sse

object SseEventTopic {
    fun cafeCartItem(
        cafeCartId: String
    ): String = "cafe-cart-item-$cafeCartId"
}