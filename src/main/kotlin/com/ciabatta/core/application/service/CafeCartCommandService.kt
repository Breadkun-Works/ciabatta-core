package com.ciabatta.core.application.service

import com.ciabatta.core.application.dto.CafeCartCreateDTO
import com.ciabatta.core.application.mapper.CafeCartMapper
import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.application.port.input.CafeCartCommandUseCase
import com.ciabatta.core.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.application.port.output.CafeCartCommandPort
import com.ciabatta.core.application.validator.CafeCartValidator
import com.ciabatta.core.global.util.KeyGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional
class CafeCartCommandService(
    private val cafeCartCommandPort: CafeCartCommandPort,
    private val cafeCartQueryUseCase: CafeCartQueryUseCase,
    private val cafeCartValidator: CafeCartValidator
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

    override suspend fun expireCafeCart(
        userUUID: String,
        cafeCartId: String
    ): CafeCart {
        val cafeCart = cafeCartQueryUseCase.getCafeCartById(cafeCartId, true)
        cafeCartValidator.assertCartIsActive(cafeCart)
        cafeCartValidator.assertCartOwnership(userUUID, cafeCart)

        val entity = CafeCartMapper.mapDomainToEntity(cafeCart.copy(expiresAt = LocalDateTime.now()))
        val savedEntity = cafeCartCommandPort.save(entity)

        return CafeCartMapper.mapEntityToDomain(savedEntity)
    }
}