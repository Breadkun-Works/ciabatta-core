package com.ciabatta.core.application.service

import com.ciabatta.core.application.mapper.CafeCartMapper
import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.application.port.output.CafeCartQueryPort
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.enums.GlobalEnums
import com.ciabatta.core.global.exception.BusinessException
import com.ciabatta.core.global.exception.ErrorCode
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CafeCartQueryService(
    private val cafeCartQueryPort: CafeCartQueryPort
) : CafeCartQueryUseCase {
    override suspend fun findCafeCartsByOptions(
        cafeLocation: GlobalEnums.Location?,
        status: CafeEnums.Cart.Status?,
        createdById: String?
    ): List<CafeCart> {
        val currentTime = LocalDateTime.now()

        return cafeCartQueryPort.findByMultipleOptions(cafeLocation, status, createdById, currentTime)
            .map { CafeCartMapper.mapEntityToDomain(it) }.toList()
    }

    override suspend fun findCafeCartById(
        cafeCartId: String
    ): CafeCart = cafeCartQueryPort.findById(cafeCartId)?.let { CafeCartMapper.mapEntityToDomain(it) }
        ?: throw BusinessException(
            ErrorCode.CA_2001, "CafeCart not found with id: $cafeCartId"
        )

}