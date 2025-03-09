package com.ciabatta.core.global.exception

import org.springframework.http.HttpStatus

abstract class CustomException(
    open val errorCode: String,
    open val status: HttpStatus,
    override val message: String? = null,
    override val cause: Throwable? = null
) : RuntimeException(message, cause)

/**
 * DTO 혹은 핸들러 계층에서 발생하는 입력 검증 오류에 사용.
 * 일반적으로 HTTP 400(Bad Request) 상태 코드와 매핑.
 */
class ValidationException(
    private val error: ErrorCode,
    override val message: String? = error.code
) : CustomException(error.code, error.httpStatus, message)

/**
 * 도메인(어플리케이션) 로직에서 발생하는 비즈니스 오류에 사용.
 * 상황에 맞게 HTTP 상태 코드를 커스터마이징 할 수 있음.
 */
class BusinessException(
    private val error: ErrorCode,
    override val message: String? = null,
    override val cause: Throwable? = null
) : CustomException(error.code, error.httpStatus, message, cause)