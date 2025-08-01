package com.ciabatta.core.cafe.application.service

import com.ciabatta.core.cafe.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.cafe.application.mapper.CafeCartItemMapper
import com.ciabatta.core.cafe.application.port.input.CafeCartItemCommandUseCase
import com.ciabatta.core.cafe.application.port.input.CafeCartItemQueryUseCase
import com.ciabatta.core.cafe.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.cafe.application.port.input.CafeMenuQueryUseCase
import com.ciabatta.core.cafe.application.port.output.CafeCartItemCommandPort
import com.ciabatta.core.cafe.application.validator.CafeValidator
import com.ciabatta.core.cafe.domain.model.CafeCartItem
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
    private val cafeValidator: CafeValidator,
) : CafeCartItemCommandUseCase {
    @Transactional
    override suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        userName: String,
        dtos: List<CafeCartItemCreateDTO>,
    ): List<CafeCartItem> {
        val cafeCart = cafeCartQueryUseCase.getCafeCartById(cartId, false)
        cafeValidator.assertCartIsActive(cafeCart)

        val createdItems =
            dtos.mapNotNull { dto ->
                val cafeMenu = cafeMenuQueryUseCase.findCafeMenuById(dto.cafeMenuId)
                if (!cafeMenu.available) return@mapNotNull null // 사용 불가 메뉴(available == false)는 스킵

                cafeValidator.assertCartAndMenuLocationMatch(cafeCart, cafeMenu)

                val domain = CafeCartItemMapper.mapCreateDTOToDomain(cartId, userUUID, userName, dto)
                val entity = CafeCartItemMapper.mapDomainToEntity(domain)
                val savedEntity = cafeCartItemCommandPort.save(entity)

                CafeCartItemMapper.mapEntityToDomain(savedEntity).attachDetails(cafeMenu)
            }

        cafeCartItemSseEventPublisher.publishCreated(createdItems) // Sse 이벤트 발행

        return createdItems
    }

    override suspend fun deleteCafeCartItems(
        userUUID: String,
        dto: DeleteIdsDTO,
    ) {
        val idsToDelete = dto.ids
        require(idsToDelete.isNotEmpty()) { "Empty CafeCartItemIDs" }

        // 첫 번째 ID로 CafeCartItem을 조회하여 해당 카트 ID 획득
        val firstCartItem =
            cafeCartItemQueryUseCase.findCafeCartItemsById(idsToDelete.first())
                ?: throw BusinessException(ErrorCode.CA_3001, "CafeCartItem not found with id: ${idsToDelete.first()}")
        val cafeCartId = firstCartItem.cafeCartId

        // 해당 카트에 속한 모든 CafeCartItem 조회
        val cafeCartItems = cafeCartItemQueryUseCase.findCafeCartItemsByCafeCartId(cafeCartId, null)
        val existingIds = cafeCartItems.mapNotNull { it.id }

        // 삭제 요청한 아이템들이 모두 동일한 카트에 속해 있는지 확인
        require(idsToDelete.all { it in existingIds }) { "CafeCartItems are associated with different CafeCarts." }

        // 카페 장바구니가 활성 상태 확인
        val cafeCart = cafeCartQueryUseCase.getCafeCartById(cafeCartId, false)
        cafeValidator.assertCartIsActive(cafeCart)

        // 카페 장바구니 아이템이 현재 사용자의 소유인지 확인
        cafeCartItems.filter { it.id in idsToDelete }.forEach {
            cafeValidator.assertCartItemOwnership(
                userUUID,
                it,
            )
        }

        cafeCartItemCommandPort.deleteAll(idsToDelete) // cafeItem 실제 삭제
        cafeCartItemSseEventPublisher.publishDeleted(cafeCart.id!!, idsToDelete) // Sse 이벤트 발행
    }
}
