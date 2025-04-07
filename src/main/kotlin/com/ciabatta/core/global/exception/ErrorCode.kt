package com.ciabatta.core.global.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val code: String, val httpStatus: HttpStatus) {
    /**
     * Validation 관련 에러 코드
     * DTO 혹은 핸들러 계층에서 발생하는 입력 검증 오류
     */
    VAL_0001("VAL-0001", HttpStatus.BAD_REQUEST), // 헤더 검증 오류
    VAL_0002("VAL-0002", HttpStatus.BAD_REQUEST), // Jackson DTO 맵핑 오류 (JSON 파싱 실패)
    VAL_0003("VAL-0003", HttpStatus.BAD_REQUEST), // Jakarta/Spring Validator 검증 오류 (DTO 필드 검증 실패)

    /**
     * Cafe 도메인(어플리케이션) 로직에서 발생하는 비즈니스 오류
     * CA-1XXX : CafeMenu 관련 오류
     * CA-2XXX : CafeCart 관련 오류
     */
    CA_1001("CA-1001", HttpStatus.NOT_FOUND), // CafeMenu가 존재하지 않을 때
    CA_2001("CA-2001", HttpStatus.NOT_FOUND), // CafeCart가 존재하지 않을 때
    CA_2002("CA-2002", HttpStatus.UNPROCESSABLE_ENTITY), // CafeCart가 Active 상태가 아닐 때
    CA_2003("CA-2003", HttpStatus.CONFLICT), // CafeCart와 CafeCartItem의 Location이 다를 때
    CA_2004("CA-2004", HttpStatus.FORBIDDEN), // CafeCart 생성자와 일치하지 않을 때
    CA_3001("CA-3001", HttpStatus.NOT_FOUND), // CafeCartItem이 존재하지 않을 때
    CA_3002("CA-3002", HttpStatus.FORBIDDEN), // CafeCartItem 생성자와 일치하지 않을 때

    /**
     * SSE 로직에서 발생하는 오류
     */
    SSE_1001("SSE-1001", HttpStatus.INTERNAL_SERVER_ERROR), // SSE 발행 오류
    SSE_1002("SSE-1002", HttpStatus.FORBIDDEN), // SSE 구독 오류
    SSE_1003("SSE-1003", HttpStatus.REQUEST_TIMEOUT), // SSE 타임아웃
}