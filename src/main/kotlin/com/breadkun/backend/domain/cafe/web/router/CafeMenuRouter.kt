package com.breadkun.backend.domain.cafe.web.router

import com.breadkun.backend.domain.cafe.web.handler.AdminCafeMenuCommandHandler
import com.breadkun.backend.domain.cafe.web.handler.CafeMenuQueryHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CafeMenuRouterConfig(
    private val cafeMenuQueryHandler: CafeMenuQueryHandler,
    private val adminCafeMenuCommandHandler: AdminCafeMenuCommandHandler
) {
    @Bean
    fun cafeMenuRouter() = coRouter {
        "/api/cafe/menus".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                GET("/board", cafeMenuQueryHandler::getCafeMenuBoardByOptions)
            }
        }
    }

    @Bean
    fun adminCafeMenuRouter() = coRouter {
        "/api/admin/cafe/menus".nest {
            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
                POST("", adminCafeMenuCommandHandler::createCafeMenu)
                PATCH("/{cafeMenuId}", adminCafeMenuCommandHandler::updateCafeMenu)
                DELETE("/{cafeMenuId}", adminCafeMenuCommandHandler::deleteCafeMenuById)
            }
        }
    }
}