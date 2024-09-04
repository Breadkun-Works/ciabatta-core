package com.breadkun.backend.domain.cafe.web.router

import com.breadkun.backend.domain.cafe.web.handler.CafeMenuCommandHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CafeMenuRouterConfig(
    private val cafeMenuCommandHandler: CafeMenuCommandHandler
) {
    @Bean
    fun cafeMenuRouter() = coRouter {
        "/api/cafe/menus".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                POST("", cafeMenuCommandHandler::createCafeMenu)
                PATCH("", cafeMenuCommandHandler::updateCafeMenu)
                DELETE("/{id}", cafeMenuCommandHandler::deleteCafeMenuById)
            }
        }
    }
}