package com.ciabatta.core.cafe.application.service

import com.ciabatta.core.cafe.application.dto.CafeCartCreateDTO
import com.ciabatta.core.cafe.application.mapper.CafeCartMapper
import com.ciabatta.core.cafe.application.port.input.CafeCartCommandUseCase
import com.ciabatta.core.cafe.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.cafe.application.port.output.CafeCartCommandPort
import com.ciabatta.core.cafe.application.validator.CafeValidator
import com.ciabatta.core.cafe.domain.model.CafeCart
import com.ciabatta.core.global.util.KeyGenerator
import java.time.LocalDateTime
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CafeCartCommandService(
    private val cafeCartCommandPort: CafeCartCommandPort,
    private val cafeCartQueryUseCase: CafeCartQueryUseCase,
    private val cafeValidator: CafeValidator,
) : CafeCartCommandUseCase {
    override suspend fun createCafeCart(
        userUUID: String,
        dto: CafeCartCreateDTO,
    ): CafeCart {
        val secureShareKey = KeyGenerator.generateSecureShareKey()

        val domain = CafeCartMapper.mapCreateDTOToDomain(userUUID, dto, secureShareKey)
        val entity = CafeCartMapper.mapDomainToEntity(domain)
        val savedEntity = cafeCartCommandPort.save(entity)

        return CafeCartMapper.mapEntityToDomain(savedEntity)
    }

    override suspend fun expireCafeCart(
        userUUID: String,
        cafeCartId: String,
    ): CafeCart {
        val cafeCart = cafeCartQueryUseCase.getCafeCartById(cafeCartId, true)
        cafeValidator.assertCartIsActive(cafeCart)
        cafeValidator.assertCartOwnership(userUUID, cafeCart)

        val entity = CafeCartMapper.mapDomainToEntity(cafeCart.copy(expiresAt = LocalDateTime.now()))
        val savedEntity = cafeCartCommandPort.save(entity)

        return CafeCartMapper.mapEntityToDomain(savedEntity)
    }
}
