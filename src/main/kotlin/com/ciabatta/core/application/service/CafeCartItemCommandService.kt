package com.ciabatta.core.application.service

import com.ciabatta.core.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.application.mapper.CafeCartItemMapper
import com.ciabatta.core.application.port.input.CafeCartItemCommandUseCase
import com.ciabatta.core.application.port.input.CafeCartItemQueryUseCase
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
    private val cafeCartValidator: CafeCartValidator,
    private val cafeCartItemQueryUseCase: CafeCartItemQueryUseCase,
    private val cafeCartItemSseEventPublisher: CafeCartItemSseEventPublisher
) : CafeCartItemCommandUseCase {
    @Transactional
    override suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        userName: String,
        dtos: List<CafeCartItemCreateDTO>
    ): List<CafeCartItem> {
        val cafeCart = cafeCartValidator.validateCart(cartId)

        val createdItems = dtos.map { dto ->
            cafeCartValidator.validateMenuAndLocation(cafeCart, dto)

            val domain = CafeCartItemMapper.mapCreateDTOToDomain(cartId, userUUID, userName, dto)
            val entity = CafeCartItemMapper.mapDomainToEntity(domain)
            val savedEntity = cafeCartItemCommandPort.save(entity)

            CafeCartItemMapper.mapEntityToDomain(savedEntity)
        }

        cafeCartItemQueryUseCase.fetchDetails(createdItems) // cartItem 상세정보 추가
        cafeCartItemSseEventPublisher.publishCreated(createdItems) // Sse 이벤트 발행

        return createdItems
    }

    override suspend fun deleteCafeCartItems(
        dto: DeleteIdsDTO
    ) {
        val idsToDelete = dto.ids
        val firstItemId = idsToDelete.first() // 삭제 될 때 모두 하나의 장바구니에서 일어남을 가정

        val cafeCartId = cafeCartValidator.validateCart(firstItemId).id
            ?: throw BusinessException(
                ErrorCode.CA_2001, "CafeCart not found with id: $firstItemId"
            )

        cafeCartItemCommandPort.deleteAll(idsToDelete) // cafeItem 실제 삭제
        cafeCartItemSseEventPublisher.publishDeleted(cafeCartId, idsToDelete) // Sse 이벤트 발행
    }


    override suspend fun deleteCafeCartItemsByCafeCartId(
        cafeCartId: String
    ) = cafeCartItemCommandPort.deleteAllByCafeCartId(cafeCartId)
}