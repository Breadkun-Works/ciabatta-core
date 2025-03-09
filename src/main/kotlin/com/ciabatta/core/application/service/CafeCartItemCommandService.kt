package com.ciabatta.core.application.service

import com.ciabatta.core.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.application.port.input.CafeCartItemCommandUseCase
import com.ciabatta.core.domain.model.CafeCartItem
import com.ciabatta.core.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.application.port.input.CafeMenuQueryUseCase
import com.ciabatta.core.application.port.output.CafeCartItemCommandPort
import com.ciabatta.core.application.validator.CafeCartValidator
import com.ciabatta.core.domain.model.CafeCart
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.dto.DeleteIdsDTO
import com.ciabatta.core.global.exception.BusinessException
import com.ciabatta.core.global.exception.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class CafeCartItemCommandService(
    private val cafeCartItemCommandPort: CafeCartItemCommandPort,
    private val cafeCartValidator: CafeCartValidator
) : CafeCartItemCommandUseCase {
    @Transactional
    override suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        userName: String,
        dtos: List<CafeCartItemCreateDTO>
    ): List<CafeCartItem> {
        val cafeCart = cafeCartValidator.validateCart(cartId)

        return dtos.map { dto ->
            cafeCartValidator.validateMenuAndLocation(cafeCart, dto)

            CafeCartItem
                .fromCreateDTO(cartId, userUUID, userName, dto)
                .toEntity()
                .let { cafeCartItemCommandPort.save(it) }
                .let { CafeCartItem.fromEntity(it) }
        }
    }

    override suspend fun deleteCafeCartItems(
        dto: DeleteIdsDTO
    ) = cafeCartItemCommandPort.deleteAll(dto.ids)


    override suspend fun deleteCafeCartItemsByCafeCartId(
        cafeCartId: String
    ) = cafeCartItemCommandPort.deleteAllByCafeCartId(cafeCartId)
}