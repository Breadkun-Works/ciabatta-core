package com.ciabatta.core.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val code: String, val httpStatus: HttpStatus) {
    /**
     * Validation 관련 에러 코드
     * DTO 혹은 핸들러 계층에서 발생하는 입력 검증 오류
     */
    VAL_0001("VAL-0001", HttpStatus.BAD_REQUEST),

    /**
     * Cafe 도메인(어플리케이션) 로직에서 발생하는 비즈니스 오류
     * CA-1XXX : CafeMenu 관련 오류
     * CA-2XXX : CafeCart 관련 오류
     */
    CA_1001("CA-1001", HttpStatus.NOT_FOUND), // CafeMenu가 존재하지 않을 때
    CA_2001("CA-2001", HttpStatus.NOT_FOUND), // CafeCart가 존재하지 않을 때
    CA_2002("CA-2002", HttpStatus.UNPROCESSABLE_ENTITY), // CafeCart가 Active 상태가 아닐 때
    CA_2003("CA-2003", HttpStatus.CONFLICT), // CafeCart와 CafeCartItem의 Location이 다를 때
}