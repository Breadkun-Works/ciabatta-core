package com.breadkun.backend.application.service

import com.breadkun.backend.domain.model.CafeCart
import com.breadkun.backend.application.port.input.CafeCartQueryUseCase
import com.breadkun.backend.application.port.output.CafeCartQueryPort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CafeCartQueryService(
    private val cafeCartQueryPort: CafeCartQueryPort
) : CafeCartQueryUseCase {
    override suspend fun findActiveCafeCartsByOptions(
        createdById: String?
    ): List<CafeCart> {
        val currentTime = LocalDateTime.now()

        return cafeCartQueryPort.findActiveByMultipleOptions(createdById, currentTime)
            .map {
                CafeCart.fromEntity(it)
            }
    }

    override suspend fun findActiveCafeCartById(
        cafeCartId: String
    ): CafeCart? {
        val currentTime = LocalDateTime.now()

        return cafeCartQueryPort.findActiveById(cafeCartId, currentTime)
            ?.let {
                CafeCart.fromEntity(it)
            }
    }
}