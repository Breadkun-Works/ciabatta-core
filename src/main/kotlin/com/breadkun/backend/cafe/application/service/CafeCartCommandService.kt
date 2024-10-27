package com.breadkun.backend.cafe.application.service

import com.breadkun.backend.cafe.application.dto.CafeCartCreateDTO
import com.breadkun.backend.cafe.domain.model.CafeCart
import com.breadkun.backend.cafe.application.port.input.CafeCartCommandUseCase
import com.breadkun.backend.cafe.application.port.output.CafeCartCommandPort
import org.springframework.stereotype.Service

@Service
class CafeCartCommandService(
    private val cafeCartCommandPort: CafeCartCommandPort
) : CafeCartCommandUseCase {
    override suspend fun createCafeCart(userUUID: String, dto: CafeCartCreateDTO): CafeCart {
        return cafeCartCommandPort.save(CafeCart.fromCreateDTO(userUUID, dto).toEntity())
            .let {
                CafeCart.fromEntity(it)
            }
    }
}