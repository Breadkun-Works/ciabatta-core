package com.ciabatta.core.global.util

import com.ciabatta.core.global.exception.ErrorCode
import com.ciabatta.core.global.exception.ValidationException
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Validator
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody

/**
 * 개별 DTO 단위의 유효성 검사를 실행하는 확장 함수
 */
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

/**
 * 리스트 형태의 DTO 개별 요소에 대해 유효성 검사를 실행하는 확장 함수
 */
suspend inline fun <reified T : Any> ServerRequest.awaitValidatedBodyList(
    validator: Validator
): List<T> {
    val body = try {
        this.awaitBody<List<T>>() // JSON 파싱 단계에서 오류가 발생하면 예외 발생
    } catch (ex: Exception) {
        throw ValidationException(
            error = ErrorCode.VAL_0002,
            message = "The request body (JSON) format is invalid or missing required fields."
        )
    }

    val errors = mutableListOf<String>()

    // 리스트 내부의 개별 DTO 요소를 검증
    body.forEachIndexed { index, item ->
        val bindingResult = BeanPropertyBindingResult(item, T::class.java.name)
        validator.validate(item, bindingResult)

        if (bindingResult.hasErrors()) {
            val fieldErrors = bindingResult.allErrors.joinToString(", ") { it.defaultMessage ?: "Invalid request." }
            errors.add("[$index]: $fieldErrors") // 에러 메시지와 인덱스를 함께 저장
        }
    }

    if (errors.isNotEmpty()) {
        throw ValidationException(
            error = ErrorCode.VAL_0003,
            message = errors.joinToString("; ")
        )
    }

    return body
}