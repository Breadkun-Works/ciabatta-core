package com.breadkun.backend.application.service

import com.breadkun.backend.domain.model.CafeCartItem
import com.breadkun.backend.application.port.input.CafeCartItemQueryUseCase
import com.breadkun.backend.application.port.input.CafeMenuQueryUseCase
import com.breadkun.backend.application.port.output.CafeCartItemQueryPort
import com.breadkun.backend.domain.model.CafeCartItemSummary
import com.breadkun.backend.global.common.enums.GlobalEnums
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service

@Service
class CafeCartItemQueryService(
    private val cafeCartItemQueryPort: CafeCartItemQueryPort,
    private val cafeMenuQueryUseCase: CafeMenuQueryUseCase
) : CafeCartItemQueryUseCase {
    override suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
        include: GlobalEnums.IncludeOption?
    ): List<CafeCartItem> {
        val cafeCartItems =
            cafeCartItemQueryPort.findByCafeCartId(cafeCartId).map { CafeCartItem.fromEntity(it) }.toList()

        if (cafeCartItems.isEmpty()) return emptyList()

        return when (include) {
            GlobalEnums.IncludeOption.DETAILS -> fetchDetails(cafeCartItems)
            else -> cafeCartItems
        }
    }

    override suspend fun findCafeCartItemSummaryByCafeCartId(
        cafeCartId: String
    ): List<CafeCartItemSummary> {
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

        return cafeCartItems.map { it.attachDetails(cafeMenuMap[it.cafeMenuId]!!) }
    }
}