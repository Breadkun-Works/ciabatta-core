package com.ciabatta.core.application.service

import com.ciabatta.core.application.dto.CafeCartCreateDTO
import com.ciabatta.core.application.mapper.CafeCartMapper
import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.application.port.input.CafeCartCommandUseCase
import com.ciabatta.core.application.port.input.CafeCartItemCommandUseCase
import com.ciabatta.core.application.port.output.CafeCartCommandPort
import com.ciabatta.core.global.dto.DeleteIdsDTO
import com.ciabatta.core.global.util.KeyGenerator
import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CafeCartCommandService(
    private val cafeCartCommandPort: CafeCartCommandPort,
    private val cafeCartItemCommandUseCase: CafeCartItemCommandUseCase
) : CafeCartCommandUseCase {
    override suspend fun createCafeCart(
        userUUID: String,
        dto: CafeCartCreateDTO
    ): CafeCart {
        val secureShareKey = KeyGenerator.generateSecureShareKey()

        val domain = CafeCartMapper.mapCreateDTOToDomain(userUUID, dto, secureShareKey)
        val entity = CafeCartMapper.mapDomainToEntity(domain)
        val savedEntity = cafeCartCommandPort.save(entity)

        return CafeCartMapper.mapEntityToDomain(savedEntity)
    }

    override suspend fun deleteCafeCarts(
        dto: DeleteIdsDTO
    ): Unit {
        dto.ids.forEach { cafeCartItemCommandUseCase.deleteCafeCartItemsByCafeCartId(it) }
        cafeCartCommandPort.deleteAll(dto.ids)
    }
}