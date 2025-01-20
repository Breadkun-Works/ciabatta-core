package com.ciabatta.core.infrastructure.web.router

import com.ciabatta.core.infrastructure.web.handler.CafeCartItemCommandHandler
import com.ciabatta.core.infrastructure.web.handler.CafeCartItemQueryHandler
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
        "/api/cafe/carts".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                "/items".nest {
                    POST("/delete", cafeCartItemCommandHandler::deleteCafeCartItems)
                }
                "/{cafeCartId}/items".nest {
                    GET("", cafeCartItemQueryHandler::findCafeCartItemsByCafeCartId)
                    GET("/summary", cafeCartItemQueryHandler::findCafeCartItemSummaryByCafeCartId)
                    POST("", cafeCartItemCommandHandler::createCafeCartItems)
                }
            }
        }
    }
}