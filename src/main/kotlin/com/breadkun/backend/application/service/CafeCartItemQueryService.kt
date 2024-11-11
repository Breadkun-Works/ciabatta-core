package com.breadkun.backend.application.service

import com.breadkun.backend.domain.model.CafeCartItem
import com.breadkun.backend.application.port.input.CafeCartItemQueryUseCase
import com.breadkun.backend.application.port.output.CafeCartItemQueryPort
import org.springframework.stereotype.Service

@Service
class CafeCartItemQueryService(
    private val cafeCartItemQueryPort: CafeCartItemQueryPort
) : CafeCartItemQueryUseCase {
    override suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
    ): List<CafeCartItem> {
        return cafeCartItemQueryPort.findByCafeCartId(cafeCartId)
            .map {
                CafeCartItem.fromEntity(it)
            }
    }
}