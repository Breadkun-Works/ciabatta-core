package com.ciabatta.core.application.port.input

import com.ciabatta.core.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.domain.model.CafeCartItem
import com.ciabatta.core.global.dto.DeleteIdsDTO

interface CafeCartItemCommandUseCase {
    suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        userName: String,
        dtos: List<CafeCartItemCreateDTO>,
    ): List<CafeCartItem>

    suspend fun deleteCafeCartItems(
        userUUID: String,
        dto: DeleteIdsDTO,
    ): Unit
}
