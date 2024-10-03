package com.breadkun.backend.cafe.application.port.input

import com.breadkun.backend.cafe.domain.model.CafeMenuBoard
import com.breadkun.backend.cafe.domain.model.CafeMenu
import com.breadkun.backend.cafe.domain.model.enums.CafeMenuCategory
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