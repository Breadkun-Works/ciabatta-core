package com.ciabatta.core.application.service

import com.ciabatta.core.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.application.mapper.CafeCartItemMapper
import com.ciabatta.core.application.port.input.CafeCartItemCommandUseCase
import com.ciabatta.core.application.port.input.CafeCartItemQueryUseCase
import com.ciabatta.core.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.application.port.input.CafeMenuQueryUseCase
import com.ciabatta.core.domain.model.CafeCartItem
import com.ciabatta.core.application.port.output.CafeCartItemCommandPort
import com.ciabatta.core.application.validator.CafeCartValidator
import com.ciabatta.core.global.dto.DeleteIdsDTO
import com.ciabatta.core.global.exception.BusinessException
import com.ciabatta.core.global.exception.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CafeCartItemCommandService(
    private val cafeCartItemCommandPort: CafeCartItemCommandPort,
    private val cafeCartItemQueryUseCase: CafeCartItemQueryUseCase,
    private val cafeMenuQueryUseCase: CafeMenuQueryUseCase,
    private val cafeCartQueryUseCase: CafeCartQueryUseCase,
    private val cafeCartItemSseEventPublisher: CafeCartItemSseEventPublisher,
    private val cafeCartValidator: CafeCartValidator
) : CafeCartItemCommandUseCase {
    @Transactional
    override suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        userName: String,
        dtos: List<CafeCartItemCreateDTO>
    ): List<CafeCartItem> {
        val cafeCart = cafeCartQueryUseCase.getCafeCartById(cartId, false)
        cafeCartValidator.assertCartIsActive(cafeCart)

        val createdItems = dtos.map { dto ->
            val cafeMenu = cafeMenuQueryUseCase.findCafeMenuById(dto.cafeMenuId)
            cafeCartValidator.assertCartAndMenuLocationMatch(cafeCart, cafeMenu)

            val domain = CafeCartItemMapper.mapCreateDTOToDomain(cartId, userUUID, userName, dto)
            val entity = CafeCartItemMapper.mapDomainToEntity(domain)
            val savedEntity = cafeCartItemCommandPort.save(entity)

            CafeCartItemMapper.mapEntityToDomain(savedEntity).attachDetails(cafeMenu)
        }

        cafeCartItemSseEventPublisher.publishCreated(createdItems) // Sse 이벤트 발행

        return createdItems
    }

    override suspend fun deleteCafeCartItems(
        dto: DeleteIdsDTO
    ): Unit {
        val idsToDelete = dto.ids
        val firstItemId = idsToDelete.first() // 삭제 될 때 모두 하나의 장바구니에서 일어남을 가정

        val cafeCartItem = cafeCartItemQueryUseCase.findCafeCartItemsById(firstItemId)
            ?: throw BusinessException(
                ErrorCode.CA_3001, "CafeCartItem not found with id: $firstItemId"
            )
        val cafeCart = cafeCartQueryUseCase.getCafeCartById(cafeCartItem.cafeCartId, false)
        cafeCartValidator.assertCartIsActive(cafeCart)

        cafeCartItemCommandPort.deleteAll(idsToDelete) // cafeItem 실제 삭제
        cafeCartItemSseEventPublisher.publishDeleted(cafeCart.id!!, idsToDelete) // Sse 이벤트 발행
    }
}