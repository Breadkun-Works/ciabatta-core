package com.breadkun.backend.infrastructure.web.router

import com.breadkun.backend.infrastructure.web.handler.CafeCartItemCommandHandler
import com.breadkun.backend.infrastructure.web.handler.CafeCartItemQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CafeCartItemRouter(
    private val cafeCartItemCommandHandler: CafeCartItemCommandHandler,
    private val cafeCartItemQueryHandler: CafeCartItemQueryHandler
) {
    @Bean
    fun cafeCartItemRoutes() = coRouter {
        "/api/cafe/carts/{cafeCartId}/items".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                GET("", cafeCartItemQueryHandler::findCafeCartItemsByCafeCartId)
                POST("", cafeCartItemCommandHandler::createCafeCartItems)
            }
        }
    }
}