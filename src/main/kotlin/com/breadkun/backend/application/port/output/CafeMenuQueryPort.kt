package com.breadkun.backend.application.port.output

import com.breadkun.backend.domain.model.CafeMenuBoard
import com.breadkun.backend.domain.model.enums.CafeEnums
import com.breadkun.backend.global.common.enums.GlobalEnums
import com.breadkun.backend.infrastructure.persistence.entity.CafeMenuEntity
import kotlinx.coroutines.flow.Flow

interface CafeMenuQueryPort {
    suspend fun findById(
        id: String
    ): CafeMenuEntity?

    fun findByIds(
        ids: Set<String>
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