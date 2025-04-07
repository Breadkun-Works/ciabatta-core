package com.ciabatta.core.infrastructure.web.router

import com.ciabatta.core.infrastructure.web.handler.CafeMenuQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CafeMenuRouter(
    private val cafeMenuQueryHandler: CafeMenuQueryHandler
) {
    @Bean
    fun cafeMenuRoutes() = coRouter {
        "/api/cafe/menus".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                GET("/board", cafeMenuQueryHandler::getCafeMenuBoardByOptions)
            }
        }
    }
}