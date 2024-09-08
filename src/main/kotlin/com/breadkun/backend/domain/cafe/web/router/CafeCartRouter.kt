//package com.breadkun.backend.domain.cafe.web.router
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.http.MediaType
//import org.springframework.web.reactive.function.server.coRouter
//
//@Configuration
//class CafeCartRouterConfig(
////    private val cafeMenuCommandHandler: CafeMenuCommandHandler,
////    private val cafeMenuQueryHandler: CafeMenuQueryHandler
//) {
//    @Bean
//    fun cafeCartRouter() = coRouter {
//        "/api/cafe/carts".nest {
//            accept(MediaType.valueOf("application/vnd.breadkun.v1+json")).nest {
////                GET("/board", cafeMenuQueryHandler::getCafeMenuBoardByOptions)
////                POST("", cafeMenuCommandHandler::createCafeMenu)
////                PATCH("/{id}", cafeMenuCommandHandler::updateCafeMenu)
////                DELETE("/{id}", cafeMenuCommandHandler::deleteCafeMenuById)
//            }
//        }
//    }
//}