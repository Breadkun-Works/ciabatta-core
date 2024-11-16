package com.breadkun.backend.application.port.input

import com.breadkun.backend.domain.model.CafeMenuBoard
import com.breadkun.backend.domain.model.CafeMenu
import com.breadkun.backend.domain.model.enums.CafeEnums
import com.breadkun.backend.global.common.enums.GlobalEnums
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

interface CafeMenuQueryUseCase {
    suspend fun findCafeMenuById(
        cafeMenuId: String
    ): CafeMenu?

    suspend fun getCafeMenuBoardByOptions(
        cafeLocation: GlobalEnums.Location?,
        name: String?,
        category: CafeEnums.Menu.Category?,
        pageable: Pageable
    ): PageImpl<CafeMenuBoard>
}