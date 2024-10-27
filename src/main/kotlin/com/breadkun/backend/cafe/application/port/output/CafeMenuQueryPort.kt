package com.breadkun.backend.cafe.application.port.output

import com.breadkun.backend.cafe.domain.model.CafeMenuBoard
import com.breadkun.backend.cafe.domain.model.enums.CafeMenuCategory
import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeMenuEntity
import com.breadkun.backend.global.common.enums.Location

interface CafeMenuQueryPort {
    suspend fun findById(id: String): CafeMenuEntity?
    suspend fun findByMultipleOptionsWithGrouping(
        cafeLocation: Location?,
        name: String?,
        category: CafeMenuCategory?,
        page: Int?,
        size: Int?
    ): List<CafeMenuBoard>

    suspend fun countByMultipleOptionsWithGrouping(
        cafeLocation: Location?,
        name: String?,
        category: CafeMenuCategory?
    ): Long
}