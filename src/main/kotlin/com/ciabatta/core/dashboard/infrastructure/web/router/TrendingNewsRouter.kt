package com.ciabatta.core.dashboard.infrastructure.web.router

import com.ciabatta.core.dashboard.infrastructure.web.handler.TrendingNewsQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class TrendingNewsRouter(
    private val trendingNewsQueryHandler: TrendingNewsQueryHandler,
) {
    @Bean
    fun trendingNewsRoutes() =
        coRouter {
            "/api/dashboard/trending-news".nest {
                accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                    GET("", trendingNewsQueryHandler::findAllTrendingNewsItems)
                }
            }
        }
}
