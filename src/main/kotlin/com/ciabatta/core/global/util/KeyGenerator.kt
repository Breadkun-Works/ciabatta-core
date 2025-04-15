package com.ciabatta.core.global.util

import java.security.SecureRandom
import java.util.Base64

object KeyGenerator {
    private val secureRandom = SecureRandom()

    /**
     * URL 공유용 암호화 키 생성 (예: 카페 장바구니 계좌번호 암호화용)
     * Base64 인코딩된 256비트 키를 생성하며, 프론트에서는 WebCrypto API 등으로 복호화에 사용됨.
     * @return Base64 인코딩된 256bit (32 bytes) 랜덤 키
     */
    fun generateSecureShareKey(): String = generateBase64Key(32)

    /**
     * 범용 Base64 키 생성기
     * @param size 바이트 단위 크기
     * @return Base64 인코딩된 문자열
     */
    fun generateBase64Key(size: Int): String {
        require(size > 0) { "Key size must be greater than 0" }
        val keyBytes = ByteArray(size)
        secureRandom.nextBytes(keyBytes)
        return Base64.getEncoder().encodeToString(keyBytes)
    }
}
