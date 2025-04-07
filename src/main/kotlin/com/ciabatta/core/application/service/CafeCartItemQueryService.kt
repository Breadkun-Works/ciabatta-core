package com.ciabatta.core.application.service

import com.ciabatta.core.application.mapper.CafeCartItemMapper
import com.ciabatta.core.domain.model.CafeCartItem
import com.ciabatta.core.application.port.input.CafeCartItemQueryUseCase
import com.ciabatta.core.application.port.input.CafeCartQueryUseCase
import com.ciabatta.core.application.port.input.CafeMenuQueryUseCase
import com.ciabatta.core.application.port.output.CafeCartItemQueryPort
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
    override suspend fun findCafeCartItemsById(
        id: String
    ): CafeCartItem? = cafeCartItemQueryPort.findById(id)?.let {
        CafeCartItemMapper.mapEntityToDomain(it)
    }

    override suspend fun findCafeCartItemsByCafeCartId(
        cafeCartId: String,
        include: GlobalEnums.IncludeOption?
    ): List<CafeCartItem> {
        cafeCartQueryUseCase.getCafeCartById(cafeCartId, false)

        val cafeCartItems =
            cafeCartItemQueryPort.findByCafeCartId(cafeCartId).map { CafeCartItemMapper.mapEntityToDomain(it) }.toList()

        if (cafeCartItems.isEmpty()) return emptyList()

        return when (include) {
            GlobalEnums.IncludeOption.DETAILS -> fetchDetails(cafeCartItems)
            else -> cafeCartItems
        }
    }

    override suspend fun fetchDetails(
        cafeCartItems: List<CafeCartItem>
    ): List<CafeCartItem> {
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