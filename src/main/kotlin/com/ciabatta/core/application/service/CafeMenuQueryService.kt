package com.ciabatta.core.application.service

import com.ciabatta.core.application.port.input.CafeMenuQueryUseCase
import com.ciabatta.core.domain.model.CafeMenuBoard
import com.ciabatta.core.domain.model.CafeMenu
import com.ciabatta.core.application.port.output.CafeMenuQueryPort
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.enums.GlobalEnums
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CafeMenuQueryService(
    private val cafeMenuQueryPort: CafeMenuQueryPort,
) : CafeMenuQueryUseCase {
    override suspend fun findCafeMenuById(
        cafeMenuId: Long
    ): CafeMenu? {
        return cafeMenuQueryPort.findById(cafeMenuId)
            ?.let {
                CafeMenu.fromEntity(it)
            }
    }

    override suspend fun findCafeMenusByIds(
        cafeMenuIds: Set<Long>
    ): List<CafeMenu> {
        if (cafeMenuIds.isEmpty()) return emptyList()

        return cafeMenuQueryPort.findByIds(cafeMenuIds).map { CafeMenu.fromEntity(it) }.toList()
    }

    override suspend fun getCafeMenuBoardByOptions(
        cafeLocation: GlobalEnums.Location?,
        name: String?,
        category: CafeEnums.Menu.Category?,
        pageable: Pageable
    ): PageImpl<CafeMenuBoard> = coroutineScope {
        val isPaged = pageable.isPaged

        val totalCountDeferred = if (isPaged) {
            async { cafeMenuQueryPort.countByMultipleOptionsWithGrouping(cafeLocation, name, category) }
        } else null

        val cafeMenuListDeferred = async {
            cafeMenuQueryPort.findByMultipleOptionsWithGrouping(
                cafeLocation = cafeLocation,
                name = name,
                category = category,
                page = if (isPaged) pageable.pageNumber else null,
                size = if (isPaged) pageable.pageSize else null
            ).toList()
        }

        val cafeMenuList = cafeMenuListDeferred.await()
        val totalCount = totalCountDeferred?.await() ?: cafeMenuList.size.toLong()

        PageImpl(
            cafeMenuList,
            if (isPaged) pageable else PageRequest.of(0, cafeMenuList.size),
            totalCount
        )
    }
}