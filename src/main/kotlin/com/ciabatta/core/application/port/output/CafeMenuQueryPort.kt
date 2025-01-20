package com.ciabatta.core.application.port.output

import com.ciabatta.core.domain.model.CafeMenuBoard
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.common.enums.GlobalEnums
import com.ciabatta.core.infrastructure.persistence.entity.CafeMenuEntity
import kotlinx.coroutines.flow.Flow

interface CafeMenuQueryPort {
    suspend fun findById(
        id: Long
    ): CafeMenuEntity?

    fun findByIds(
        ids: Set<Long>
    ): Flow<CafeMenuEntity>

    fun findByMultipleOptionsWithGrouping(
        cafeLocation: GlobalEnums.Location?,
        name: String?,
        category: CafeEnums.Menu.Category?,
        page: Int?,
        size: Int?
    ): Flow<CafeMenuBoard>

    suspend fun countByMultipleOptionsWithGrouping(
        cafeLocation: GlobalEnums.Location?,
        name: String?,
        category: CafeEnums.Menu.Category?
    ): Long
}