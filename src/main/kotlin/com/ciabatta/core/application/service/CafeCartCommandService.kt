package com.ciabatta.core.application.service

import com.ciabatta.core.application.dto.CafeCartCreateDTO
import com.ciabatta.core.application.mapper.CafeCartMapper
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
    ): CafeCart {
        val domain = CafeCartMapper.mapCreateDTOToDomain(userUUID, dto)
        val entity = CafeCartMapper.mapDomainToEntity(domain)
        val savedEntity = cafeCartCommandPort.save(entity)

        return CafeCartMapper.mapEntityToDomain(savedEntity)
    }

    @Transactional
    override suspend fun deleteCafeCarts(
        dto: DeleteIdsDTO
    ) {
        dto.ids.forEach { cafeCartItemCommandUseCase.deleteCafeCartItemsByCafeCartId(it) }
        cafeCartCommandPort.deleteAll(dto.ids)
    }
}