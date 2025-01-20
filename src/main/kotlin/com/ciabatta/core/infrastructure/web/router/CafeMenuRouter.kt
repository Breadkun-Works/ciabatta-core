package com.ciabatta.core.infrastructure.web.router

import com.ciabatta.core.infrastructure.web.handler.AdminCafeMenuCommandHandler
import com.ciabatta.core.infrastructure.web.handler.CafeMenuQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CafeMenuRouter(
    private val cafeMenuQueryHandler: CafeMenuQueryHandler,
    private val adminCafeMenuCommandHandler: AdminCafeMenuCommandHandler
) {
    @Bean
    fun cafeMenuRoutes() = coRouter {
        "/api/cafe/menus".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                GET("/board", cafeMenuQueryHandler::getCafeMenuBoardByOptions)
            }
        }
    }

    @Bean
    fun adminCafeMenuRoutes() = coRouter {
        "/api/admin/cafe/menus".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                POST("", adminCafeMenuCommandHandler::createCafeMenu)
                PATCH("/{cafeMenuId}", adminCafeMenuCommandHandler::updateCafeMenu)
                DELETE("/{cafeMenuId}", adminCafeMenuCommandHandler::deleteCafeMenuById)
            }
        }
    }
}