package com.ciabatta.core.global.util

import com.ciabatta.core.global.exception.ErrorCode
import com.ciabatta.core.global.exception.ValidationException
import org.springframework.web.reactive.function.server.ServerRequest
import java.util.*

object HeaderUtils {
    /**
     * 요청 헤더에서 지정된 이름의 값을 가져옵니다.
     * 값이 없거나 공백인 경우 ValidationException을 발생시킵니다.
     *
     * @param headerName 가져올 헤더 이름
     * @param request 현재 요청 객체
     * @return 헤더의 문자열 값
     * @throws ValidationException 지정된 헤더가 없거나 빈 값일 경우
     */
    fun getHeader(headerName: String, request: ServerRequest): String {
        return request.headers().firstHeader(headerName)
            ?.trim()
            ?.takeIf { it.isNotBlank() }
            ?: throw ValidationException(ErrorCode.VAL_0001, "Missing $headerName header")
    }

    /**
     * 요청 헤더에서 Base64로 인코딩된 값을 가져와 UTF-8로 디코딩한 문자열을 반환합니다.
     * 값이 없거나 디코딩에 실패할 경우 ValidationException을 발생시킵니다.
     *
     * @param headerName 가져올 헤더 이름
     * @param request 현재 요청 객체
     * @return Base64 디코딩된 문자열 값
     * @throws ValidationException 지정된 헤더가 없거나 Base64 디코딩 실패 시
     */
    fun getBase64Header(headerName: String, request: ServerRequest): String {
        val encodedValue = request.headers().firstHeader(headerName)
            ?.takeIf { it.isNotBlank() }
            ?: throw ValidationException(ErrorCode.VAL_0001, "Missing $headerName header")

        return try {
            String(Base64.getDecoder().decode(encodedValue), Charsets.UTF_8).trim()
        } catch (e: IllegalArgumentException) {
            throw ValidationException(ErrorCode.VAL_0001, "Invalid Base64 encoding in $headerName header")
        }
    }
}
