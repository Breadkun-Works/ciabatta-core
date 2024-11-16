package com.breadkun.backend.application.port.input

import com.breadkun.backend.application.dto.CafeMenuCreateDTO
import com.breadkun.backend.application.dto.CafeMenuUpdateDTO
import com.breadkun.backend.domain.model.CafeMenu

interface CafeMenuCommandUseCase {
    suspend fun createCafeMenu(
        userID: String,
        dto: CafeMenuCreateDTO
    ): CafeMenu

    suspend fun updateCafeMenu(
        cafeMenuId: String,
        userID: String,
        dto: CafeMenuUpdateDTO
    ): CafeMenu?

    suspend fun deleteCafeMenuById(
        cafeMenuId: String
    ): String?
}