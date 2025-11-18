package com.ciabatta.core.feed.infrastructure.web.router

import com.ciabatta.core.feed.infrastructure.web.handler.DailyBakeryQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class DailyBakeryRouter(
    private val dailyBakeryQueryHandler: DailyBakeryQueryHandler,
) {
    @Bean
    fun dailyBakeryRoutes() =
        coRouter {
            "/api/feed/daily-bakery".nest {
                accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                    GET("/today", dailyBakeryQueryHandler::findTodayBakery)
                }
            }
        }
}
