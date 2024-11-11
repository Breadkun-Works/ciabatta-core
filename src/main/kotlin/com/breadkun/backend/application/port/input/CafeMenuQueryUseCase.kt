package com.breadkun.backend.application.port.input

import com.breadkun.backend.domain.model.CafeMenuBoard
import com.breadkun.backend.domain.model.CafeMenu
import com.breadkun.backend.domain.model.enums.CafeMenuCategory
import com.breadkun.backend.global.common.enums.Location
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

interface CafeMenuQueryUseCase {
    suspend fun findCafeMenuById(cafeMenuId: String): CafeMenu?
    suspend fun getCafeMenuBoardByOptions(
        cafeLocation: Location?,
        name: String?,
        category: CafeMenuCategory?,
        pageable: Pageable
    ): PageImpl<CafeMenuBoard>
}