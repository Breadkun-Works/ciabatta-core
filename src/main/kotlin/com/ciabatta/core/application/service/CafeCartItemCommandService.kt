package com.ciabatta.core.application.service

import com.ciabatta.core.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.application.mapper.CafeCartItemMapper
import com.ciabatta.core.application.port.input.CafeCartItemCommandUseCase
import com.ciabatta.core.domain.model.CafeCartItem
import com.ciabatta.core.application.port.output.CafeCartItemCommandPort
import com.ciabatta.core.application.validator.CafeCartValidator
import com.ciabatta.core.global.dto.DeleteIdsDTO
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

            val domain = CafeCartItemMapper.mapCreateDTOToDomain(cartId, userUUID, userName, dto)
            val entity = CafeCartItemMapper.mapDomainToEntity(domain)
            val savedEntity = cafeCartItemCommandPort.save(entity)

            CafeCartItemMapper.mapEntityToDomain(savedEntity)
        }
    }

    override suspend fun deleteCafeCartItems(
        dto: DeleteIdsDTO
    ) = cafeCartItemCommandPort.deleteAll(dto.ids)


    override suspend fun deleteCafeCartItemsByCafeCartId(
        cafeCartId: String
    ) = cafeCartItemCommandPort.deleteAllByCafeCartId(cafeCartId)
}