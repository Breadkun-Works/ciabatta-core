package com.ciabatta.core.cafe.application.port.output

import com.ciabatta.core.cafe.domain.model.CafeMenuBoard
import com.ciabatta.core.cafe.domain.model.enums.CafeEnums
import com.ciabatta.core.cafe.infrastructure.persistence.entity.CafeMenuEntity
import com.ciabatta.core.global.enums.GlobalEnums
import kotlinx.coroutines.flow.Flow

interface CafeMenuQueryPort {
    suspend fun findById(id: Long): CafeMenuEntity?

    fun findByIds(ids: Set<Long>): Flow<CafeMenuEntity>

    fun findByMultipleOptionsWithGrouping(
        cafeLocation: GlobalEnums.Location?,
        name: String?,
        category: CafeEnums.Menu.Category?,
        page: Int?,
        size: Int?,
    ): Flow<CafeMenuBoard>

    suspend fun countByMultipleOptionsWithGrouping(
        cafeLocation: GlobalEnums.Location?,
        name: String?,
        category: CafeEnums.Menu.Category?,
    ): Long
}
