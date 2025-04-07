package com.ciabatta.core.application.service

import com.ciabatta.core.application.mapper.CafeCartMapper
import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.application.port.output.CafeCartQueryPort
import com.ciabatta.core.global.exception.BusinessException
import com.ciabatta.core.global.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class CafeCartQueryService(
    private val cafeCartQueryPort: CafeCartQueryPort
) : CafeCartQueryUseCase {
    override suspend fun getCafeCartById(
        cafeCartId: String,
        includeSecureKey: Boolean
    ): CafeCart = cafeCartQueryPort.findById(cafeCartId)?.let { CafeCartMapper.mapEntityToDomain(it, includeSecureKey) }
        ?: throw BusinessException(
            ErrorCode.CA_2001, "CafeCart not found with id: $cafeCartId"
        )
}