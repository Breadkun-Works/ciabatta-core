package com.breadkun.backend.application.port.input

import com.breadkun.backend.application.dto.CafeCartItemCreateDTO
import com.breadkun.backend.domain.model.CafeCartItem
import com.breadkun.backend.global.common.dto.DeleteIdsDTO

interface CafeCartItemCommandUseCase {
    suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        userName: String,
        dtos: List<CafeCartItemCreateDTO>
    ): List<CafeCartItem>

    suspend fun deleteCafeCartItems(
        dto: DeleteIdsDTO
    )

    suspend fun deleteCafeCartItemsByCafeCartId(
        cafeCartId: String
    )
}