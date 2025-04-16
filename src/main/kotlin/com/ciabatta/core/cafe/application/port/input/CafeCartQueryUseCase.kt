package com.ciabatta.core.cafe.application.port.input

import com.ciabatta.core.cafe.domain.model.CafeCart

fun interface CafeCartQueryUseCase {
    suspend fun getCafeCartById(
        cafeCartId: String,
        includeSecureKey: Boolean,
    ): CafeCart
}
