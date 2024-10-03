package com.breadkun.backend.cafe.application.service

import com.breadkun.backend.cafe.domain.model.CafeMenuBoard
import com.breadkun.backend.cafe.domain.model.CafeMenu
import com.breadkun.backend.cafe.application.port.input.CafeMenuQueryUseCase
import com.breadkun.backend.cafe.application.port.output.CafeMenuQueryPort
import com.breadkun.backend.global.common.enums.Location
import com.breadkun.backend.cafe.domain.model.enums.CafeMenuCategory
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CafeMenuQueryService(
    private val cafeMenuQueryPort: CafeMenuQueryPort,
) : CafeMenuQueryUseCase {
    override suspend fun findCafeMenuById(cafeMenuId: String): CafeMenu? {
        return cafeMenuQueryPort.findById(cafeMenuId)
            ?.let {
                CafeMenu.fromEntity(it)
            }
    }

    override suspend fun getCafeMenuBoardByOptions(
        cafeLocation: Location?,
        name: String?,
        category: CafeMenuCategory?,
        pageable: Pageable
    ): PageImpl<CafeMenuBoard> = coroutineScope {
        val totalCountDeferred = if (pageable.isPaged) {
            async { cafeMenuQueryPort.countByMultipleOptionsWithGrouping(cafeLocation, name, category) }
        } else {
            null
        }

        val cafeMenuListDeferred = async {
            cafeMenuQueryPort.findByMultipleOptionsWithGrouping(
                cafeLocation, name, category,
                if (pageable.isPaged) pageable.pageNumber else null,
                if (pageable.isPaged) pageable.pageSize else null
            )
        }

        val totalCount = totalCountDeferred?.await() ?: cafeMenuListDeferred.await().size.toLong()
        val cafeMenuList = cafeMenuListDeferred.await()

        PageImpl(
            cafeMenuList,
            if (pageable.isPaged) pageable else PageRequest.of(0, cafeMenuList.size),
            totalCount
        )
    }
}