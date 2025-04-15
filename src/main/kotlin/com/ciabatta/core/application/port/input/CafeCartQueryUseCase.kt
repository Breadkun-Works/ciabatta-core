package com.ciabatta.core.application.port.input

import com.ciabatta.core.domain.model.CafeCart

fun interface CafeCartQueryUseCase {
    suspend fun getCafeCartById(
        cafeCartId: String,
        includeSecureKey: Boolean,
    ): CafeCart
}
