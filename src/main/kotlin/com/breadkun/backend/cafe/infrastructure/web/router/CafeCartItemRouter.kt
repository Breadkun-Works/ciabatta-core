package com.breadkun.backend.cafe.infrastructure.web.router

import com.breadkun.backend.cafe.infrastructure.web.handler.CafeCartItemCommandHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CafeCartItemRouter(
    private val cafeCartItemCommandHandler: CafeCartItemCommandHandler
){
    @Bean
    fun cafeCartItemRoutes() = coRouter {
        "/api/cafe/carts/{cartId}/items".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                POST("", cafeCartItemCommandHandler::createCafeCartItems)
            }
        }
    }
}