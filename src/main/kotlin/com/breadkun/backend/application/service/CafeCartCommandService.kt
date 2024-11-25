package com.breadkun.backend.application.service

import com.breadkun.backend.application.dto.CafeCartCreateDTO
import com.breadkun.backend.domain.model.CafeCart
import com.breadkun.backend.application.port.input.CafeCartCommandUseCase
import com.breadkun.backend.application.port.input.CafeCartItemCommandUseCase
import com.breadkun.backend.application.port.output.CafeCartCommandPort
import com.breadkun.backend.global.common.dto.DeleteIdsDTO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
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
        return cafeCartCommandPort.save(CafeCart.fromCreateDTO(userUUID, dto).toEntity())
            .let {
                CafeCart.fromEntity(it)
            }
    }

    @Transactional
    override suspend fun deleteCafeCarts(
        dto: DeleteIdsDTO
    ) = coroutineScope {
        try {
            dto.ids.map {
                launch {
                    try {
                        cafeCartItemCommandUseCase.deleteCafeCartItemsByCafeCartId(it)
                    } catch (e: Exception) {
                        throw e
                    }
                }
            }.joinAll()

            cafeCartCommandPort.deleteAll(dto.ids)
        } catch (e: Exception) {
            throw e
        }
    }
}