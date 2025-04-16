package com.ciabatta.core.cafe.application.service

import com.ciabatta.core.cafe.application.mapper.CafeCartMapper
import com.ciabatta.core.cafe.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.cafe.application.port.output.CafeCartQueryPort
import com.ciabatta.core.cafe.domain.model.CafeCart
import com.ciabatta.core.global.exception.BusinessException
import com.ciabatta.core.global.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class CafeCartQueryService(
    private val cafeCartQueryPort: CafeCartQueryPort,
) : CafeCartQueryUseCase {
    override suspend fun getCafeCartById(
        cafeCartId: String,
        includeSecureKey: Boolean,
    ): CafeCart =
        cafeCartQueryPort.findById(cafeCartId)?.let { CafeCartMapper.mapEntityToDomain(it, includeSecureKey) }
            ?: throw BusinessException(
                ErrorCode.CA_2001, "CafeCart not found with id: $cafeCartId",
            )
}
