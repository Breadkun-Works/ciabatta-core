package com.breadkun.backend.domain.cafe.web.router

import com.breadkun.backend.domain.cafe.web.handler.CafeMenuCommandHandler
import com.breadkun.backend.domain.cafe.web.handler.CafeMenuQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CafeMenuRouterConfig(
    private val cafeMenuCommandHandler: CafeMenuCommandHandler,
    private val cafeMenuQueryHandler: CafeMenuQueryHandler
) {
    @Bean
    fun cafeMenuRouter() = coRouter {
        "/api/cafe/menus".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                GET("/board", cafeMenuQueryHandler::getCafeMenuBoardByOptions)
                POST("", cafeMenuCommandHandler::createCafeMenu)
                PATCH("/{id}", cafeMenuCommandHandler::updateCafeMenu)
                DELETE("/{id}", cafeMenuCommandHandler::deleteCafeMenuById)
            }
        }
    }
}