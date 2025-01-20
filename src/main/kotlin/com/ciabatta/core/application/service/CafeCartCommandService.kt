package com.ciabatta.core.application.service

import com.ciabatta.core.application.dto.CafeCartCreateDTO
import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.application.port.input.CafeCartCommandUseCase
import com.ciabatta.core.application.port.input.CafeCartItemCommandUseCase
import com.ciabatta.core.application.port.output.CafeCartCommandPort
import com.ciabatta.core.global.common.dto.DeleteIdsDTO
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