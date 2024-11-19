package com.breadkun.backend.application.service

import com.breadkun.backend.application.dto.CafeCartCreateDTO
import com.breadkun.backend.domain.model.CafeCart
import com.breadkun.backend.application.port.input.CafeCartCommandUseCase
import com.breadkun.backend.application.port.output.CafeCartCommandPort
import com.breadkun.backend.global.common.dto.DeleteIdsDTO
import org.springframework.stereotype.Service

@Service
class CafeCartCommandService(
    private val cafeCartCommandPort: CafeCartCommandPort
) : CafeCartCommandUseCase {
    override suspend fun createCafeCart(
        userUUID: String,
        dto: CafeCartCreateDTO
    ): CafeCart {
        return cafeCartCommandPort.save(CafeCart.fromCreateDTO(userUUID, dto).toEntity())
            .let {
                CafeCart.fromEntity(it)
            }
    }

    override suspend fun deleteCafeCarts(
        dto: DeleteIdsDTO
    ) {
        return cafeCartCommandPort.deleteAll(dto.ids)
    }
}