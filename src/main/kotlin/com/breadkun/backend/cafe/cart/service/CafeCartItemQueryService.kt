package com.breadkun.backend.cafe.cart.service

import com.breadkun.backend.cafe.cart.dto.response.CafeCartItemDTO
import com.breadkun.backend.domain.cafe.cart.repository.CafeCartItemQueryRepository
import org.springframework.stereotype.Service

fun interface CafeCartItemQueryService {
    suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
    ): List<com.breadkun.backend.cafe.cart.dto.response.CafeCartItemDTO>
}

@Service
class CafeCartItemQueryServiceImpl(
    private val cafeCartItemQueryRepository: CafeCartItemQueryRepository
) : CafeCartItemQueryService {
    override suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
    ): List<com.breadkun.backend.cafe.cart.dto.response.CafeCartItemDTO> {
        return cafeCartItemQueryRepository.findByCafeCartId(cafeCartId)
            .map {
                com.breadkun.backend.cafe.cart.dto.response.CafeCartItemDTO.fromModel(it)
            }
    }
}