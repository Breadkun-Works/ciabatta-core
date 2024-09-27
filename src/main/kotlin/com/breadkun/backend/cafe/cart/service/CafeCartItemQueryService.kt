package com.breadkun.backend.cafe.cart.service

import com.breadkun.backend.domain.cafe.cart.dto.response.CafeCartItemDTO
import com.breadkun.backend.domain.cafe.cart.repository.CafeCartItemQueryRepository
import org.springframework.stereotype.Service

fun interface CafeCartItemQueryService {
    suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
    ): List<CafeCartItemDTO>
}

@Service
class CafeCartItemQueryServiceImpl(
    private val cafeCartItemQueryRepository: CafeCartItemQueryRepository
) : CafeCartItemQueryService {
    override suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
    ): List<CafeCartItemDTO> {
        return cafeCartItemQueryRepository.findByCafeCartId(cafeCartId)
            .map {
                CafeCartItemDTO.fromModel(it)
            }
    }
}