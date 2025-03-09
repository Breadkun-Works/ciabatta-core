package com.ciabatta.core.global.util

import com.ciabatta.core.global.exception.ErrorCode
import com.ciabatta.core.global.exception.ValidationException
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Validator
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody

suspend inline fun <reified T : Any> ServerRequest.awaitValidatedBody(
    validator: Validator
): T {
    val body = try {
        this.awaitBody<T>()  // Jackson JSON 변환 오류 발생 시
    } catch (ex: Exception) {
        // Jackson DTO 맵핑 오류 (JSON 파싱 실패)
        throw ValidationException(
            error = ErrorCode.VAL_0002,
            message = "The request body (JSON) format is invalid or missing required fields."
        )
    }

    val errors = BeanPropertyBindingResult(body, T::class.java.name)
    validator.validate(body, errors)

    // Jakarta/Spring Validator 검증 오류 (DTO 필드 검증 실패)
    if (errors.hasErrors()) {
        val errorMessages = errors.allErrors.joinToString(", ") { it.defaultMessage ?: "Invalid request." }

        throw ValidationException(error = ErrorCode.VAL_0003, message = errorMessages)
    }

    return body
}