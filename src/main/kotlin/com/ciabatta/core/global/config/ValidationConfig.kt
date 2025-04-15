package com.ciabatta.core.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.validation.Validator
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

@Configuration
class ValidationConfig {
    @Bean
    fun validator(): Validator = LocalValidatorFactoryBean()
}
