//package com.breadkun.backend.global.common.exception
//
//import com.breadkun.backend.domain.cafe.exception.CafeDomainException
//import org.springframework.http.HttpStatus
//import org.springframework.validation.FieldError
//import org.springframework.web.bind.MethodArgumentNotValidException
//import org.springframework.web.bind.annotation.ExceptionHandler
//import org.springframework.web.bind.annotation.ResponseStatus
//import org.springframework.web.bind.annotation.RestControllerAdvice
//import reactor.core.publisher.Mono
//
//@RestControllerAdvice
//class GlobalExceptionHandler {
//    @ExceptionHandler(MethodArgumentNotValidException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Mono<ValidationErrorResponse> {
//        val fieldErrors = ex.bindingResult.allErrors.map { error ->
//            FieldErrorDetail(
//                field = (error as FieldError).field,
//                errorCode = "INVALID_FIELD",
//                errorMessage = error.defaultMessage ?: "Invalid value"
//            )
//        }
//        return Mono.just(
//            ValidationErrorResponse(
//                errorCode = "VALIDATION_FAILED",
//                message = "Validation failed for request",
//                fieldErrors = fieldErrors
//            )
//        )
//    }
//
//    @ExceptionHandler(CafeDomainException::class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    fun handleCafeDomainExceptions(ex: CafeDomainException): Mono<ValidationErrorResponse> {
//        return Mono.just(
//            ValidationErrorResponse(
//                errorCode = "CAFE_DOMAIN_ERROR",
//                message = ex.message ?: "A cafe domain error occurred",
//                fieldErrors = emptyList()
//            )
//        )
//    }
//
//    @ExceptionHandler(Exception::class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    fun handleGeneralExceptions(ex: Exception): Mono<ValidationErrorResponse> {
//        return Mono.just(
//            ValidationErrorResponse(
//                errorCode = "INTERNAL_SERVER_ERROR",
//                message = "An unexpected error occurred: ${ex.message}",
//                fieldErrors = emptyList()
//            )
//        )
//    }
//}