package com.breadkun.backend.application.service

import com.breadkun.backend.domain.model.CafeCartItem
import com.breadkun.backend.application.port.input.CafeCartItemQueryUseCase
import com.breadkun.backend.application.port.input.CafeMenuQueryUseCase
import com.breadkun.backend.application.port.output.CafeCartItemQueryPort
import com.breadkun.backend.global.common.enums.GlobalEnums
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
        val cafeCartItems = cafeCartItemQueryPort.findByCafeCartId(cafeCartId).map { CafeCartItem.fromEntity(it) }
            .takeIf { it.isNotEmpty() } ?: return emptyList()

        return when (include) {
            GlobalEnums.IncludeOption.DETAILS -> fetchDetails(cafeCartItems)
            else -> cafeCartItems
        }
    }

    private suspend fun fetchDetails(cafeCartItems: List<CafeCartItem>): List<CafeCartItem> {
        val cafeMenuMap = cafeMenuQueryUseCase
            .findCafeMenusByIds(cafeCartItems.map { it.cafeMenuId }.toSet())
            .associateBy { it.id }

        return cafeCartItems.map { it.attachDetails(cafeMenuMap[it.cafeMenuId]!!) }
    }
}