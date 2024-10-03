package com.breadkun.backend.cafe.application.port.input

import com.breadkun.backend.cafe.application.dto.request.CafeMenuCreateDTO
import com.breadkun.backend.cafe.application.dto.request.CafeMenuUpdateDTO
import com.breadkun.backend.cafe.domain.model.CafeMenu

interface CafeMenuCommandUseCase {
    suspend fun createCafeMenu(userID: String, dto: CafeMenuCreateDTO): CafeMenu
    suspend fun updateCafeMenu(cafeMenuId: String, userID: String, dto: CafeMenuUpdateDTO): CafeMenu?
    suspend fun deleteCafeMenuById(cafeMenuId: String): String?
}