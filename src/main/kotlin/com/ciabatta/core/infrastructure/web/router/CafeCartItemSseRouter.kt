package com.ciabatta.core.infrastructure.web.router

import com.ciabatta.core.infrastructure.web.handler.CafeCartItemSseHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CafeCartItemSseRouter(
    private val cafeCartItemSseHandler: CafeCartItemSseHandler
) {
    @Bean
    fun cafeCartItemSseRoutes() = coRouter {
        "/sse/cafe/carts/{cafeCartId}/items".nest {
            GET("/subscribe", cafeCartItemSseHandler::subscribeCafeCartItem)
        }
    }
}