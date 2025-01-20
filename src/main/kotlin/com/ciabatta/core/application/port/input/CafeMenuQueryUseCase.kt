package com.ciabatta.core.application.port.input

import com.ciabatta.core.domain.model.CafeMenuBoard
import com.ciabatta.core.domain.model.CafeMenu
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.common.enums.GlobalEnums
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

interface CafeMenuQueryUseCase {
    suspend fun findCafeMenuById(
        cafeMenuId: Long
    ): CafeMenu?

    suspend fun findCafeMenusByIds(
        cafeMenuIds: Set<Long>
    ): List<CafeMenu>

    suspend fun getCafeMenuBoardByOptions(
        cafeLocation: GlobalEnums.Location?,
        name: String?,
        category: CafeEnums.Menu.Category?,
        pageable: Pageable
    ): PageImpl<CafeMenuBoard>
}