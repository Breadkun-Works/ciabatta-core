package com.ciabatta.core.global.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource

/**
 * 글로벌 CORS 설정을 위한 구성 클래스
 * 스프링 컨텍스트에 CorsWebFilter 빈(bean)을 등록하여 CORS 정책을 적용.
 * 환경변수(CORS_ALLOWED_ORIGINS)로 Origin 목록을 설정할 수 있음.
 * ⚠️ 프로덕션에서는 프록시(Nginx)에서 CORS 설정 전체 위임하고 있음.
 * ⚠️ 아래 설정은 테스트를 위한 설정이며, 기본적으로 VM 방화벽을 통해 백엔드서버(WAS)에 직접 접근을 차단하고 있음.
 */
@Configuration
class CorsConfig(
    @Value("\${CORS_ALLOWED_ORIGINS}") private val allowedOrigins: String
) {
    @Bean
    fun corsWebFilter(): CorsWebFilter {
        // 환경 변수로 전달받은 Origin 목록을 쉼표(,)로 분리하여 리스트로 변환
        val originList = allowedOrigins.split(",").map { it.trim() }

        val corsConfiguration = CorsConfiguration().apply {
            allowedOrigins = originList
            allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
            allowedHeaders = listOf("*")
            allowCredentials = true
        }

        val source = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfiguration)
        }

        return CorsWebFilter(source)
    }
}