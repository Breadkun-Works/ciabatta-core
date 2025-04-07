package com.ciabatta.core.infrastructure.web.router

import com.ciabatta.core.infrastructure.web.handler.CafeCartCommandHandler
import com.ciabatta.core.infrastructure.web.handler.CafeCartQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CafeCartRouter(
    private val cafeCartCommandHandler: CafeCartCommandHandler,
    private val cafeCartQueryHandler: CafeCartQueryHandler
) {
    @Bean
    fun cafeCartRoutes() = coRouter {
        "/api/cafe/carts".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                GET("/{cafeCartId}", cafeCartQueryHandler::getCafeCartById)
                POST("", cafeCartCommandHandler::createCafeCart)
                PATCH("/{cafeCartId}/expire", cafeCartCommandHandler::expireCafeCart)
            }
        }
    }
}