package com.ciabatta.core.application.port.input

import com.ciabatta.core.application.dto.CafeMenuCreateDTO
import com.ciabatta.core.application.dto.CafeMenuUpdateDTO
import com.ciabatta.core.domain.model.CafeMenu

interface CafeMenuCommandUseCase {
    suspend fun createCafeMenu(
        userID: String,
        dto: CafeMenuCreateDTO
    ): CafeMenu

    suspend fun updateCafeMenu(
        cafeMenuId: Long,
        userID: String,
        dto: CafeMenuUpdateDTO
    ): CafeMenu

    suspend fun deleteCafeMenuById(
        cafeMenuId: Long
    ): Long
}