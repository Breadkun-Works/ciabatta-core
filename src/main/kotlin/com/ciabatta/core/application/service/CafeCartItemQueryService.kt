package com.ciabatta.core.application.service

import com.ciabatta.core.application.mapper.CafeCartItemMapper
import com.ciabatta.core.domain.model.CafeCartItem
import com.ciabatta.core.application.port.input.CafeCartItemQueryUseCase
import com.ciabatta.core.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.application.port.input.CafeMenuQueryUseCase
import com.ciabatta.core.application.port.output.CafeCartItemQueryPort
import com.ciabatta.core.domain.model.CafeCartItemSummary
import com.ciabatta.core.global.enums.GlobalEnums
import com.ciabatta.core.global.exception.BusinessException
import com.ciabatta.core.global.exception.ErrorCode
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class CafeCartItemQueryService(
    private val cafeCartItemQueryPort: CafeCartItemQueryPort,
    private val cafeMenuQueryUseCase: CafeMenuQueryUseCase,
    private val cafeCartQueryUseCase: CafeCartQueryUseCase
) : CafeCartItemQueryUseCase {
    override suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
        include: GlobalEnums.IncludeOption?
    ): List<CafeCartItem> {
        cafeCartQueryUseCase.findCafeCartById(cafeCartId) // 장바구니가 존재하지 않으면 예외 발생

        val cafeCartItems =
            cafeCartItemQueryPort.findByCafeCartId(cafeCartId).map { CafeCartItemMapper.mapEntityToDomain(it) }.toList()

        if (cafeCartItems.isEmpty()) return emptyList()

        return when (include) {
            GlobalEnums.IncludeOption.DETAILS -> fetchDetails(cafeCartItems)
            else -> cafeCartItems
        }
    }

    override suspend fun findCafeCartItemSummaryByCafeCartId(
        cafeCartId: String
    ): List<CafeCartItemSummary> {
        cafeCartQueryUseCase.findCafeCartById(cafeCartId) // 장바구니가 존재하지 않으면 예외 발생

        val cafeCartItems = findCafeCartItemsByCafeCartId(
            cafeCartId,
            GlobalEnums.IncludeOption.DETAILS
        ).takeIf { it.isNotEmpty() } ?: return emptyList()

        return cafeCartItems.groupBy { Pair(it.cafeMenuId, it.isPersonalCup) }.map { (_, groupedItems) ->
            CafeCartItemSummary.fromCafeCartItems(groupedItems)
        }.sortedWith(
            compareBy(
                { it.category },
                { it.name },
                { it.drinkTemperature },
                { it.isPersonalCup }
            )
        )
    }

    private suspend fun fetchDetails(cafeCartItems: List<CafeCartItem>): List<CafeCartItem> {
        val cafeMenuMap = cafeMenuQueryUseCase
            .findCafeMenusByIds(cafeCartItems.map { it.cafeMenuId }.toSet())
            .associateBy { it.id }

        return cafeCartItems.map { item ->
            val menu = cafeMenuMap[item.cafeMenuId]
                ?: throw BusinessException(ErrorCode.CA_1001, "CafeMenu not found with id: $item.cafeMenuId")
            item.attachDetails(menu)
        }
    }
}