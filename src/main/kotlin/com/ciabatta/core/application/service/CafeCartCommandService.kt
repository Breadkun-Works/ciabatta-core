package com.ciabatta.core.application.service

import com.ciabatta.core.application.dto.CafeCartCreateDTO
import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.application.port.input.CafeCartCommandUseCase
import com.ciabatta.core.application.port.input.CafeCartItemCommandUseCase
import com.ciabatta.core.application.port.output.CafeCartCommandPort
import com.ciabatta.core.global.dto.DeleteIdsDTO
import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafeCartCommandService(
    private val cafeCartCommandPort: CafeCartCommandPort,
    private val cafeCartItemCommandUseCase: CafeCartItemCommandUseCase
) : CafeCartCommandUseCase {
    override suspend fun createCafeCart(
        userUUID: String,
        dto: CafeCartCreateDTO
    ): CafeCart = cafeCartCommandPort.save(CafeCart.fromCreateDTO(userUUID, dto).toEntity())
        .let { CafeCart.fromEntity(it) }


    @Transactional
    override suspend fun deleteCafeCarts(
        dto: DeleteIdsDTO
    ) {
        dto.ids.forEach { cafeCartItemCommandUseCase.deleteCafeCartItemsByCafeCartId(it) }
        cafeCartCommandPort.deleteAll(dto.ids)
    }
}