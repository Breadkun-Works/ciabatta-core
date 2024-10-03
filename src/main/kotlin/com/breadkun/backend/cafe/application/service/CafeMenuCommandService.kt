package com.breadkun.backend.cafe.application.service

import com.breadkun.backend.cafe.application.dto.request.CafeMenuCreateDTO
import com.breadkun.backend.cafe.application.dto.request.CafeMenuUpdateDTO
import com.breadkun.backend.cafe.domain.model.CafeMenu
import com.breadkun.backend.cafe.application.port.input.CafeMenuCommandUseCase
import com.breadkun.backend.cafe.application.port.input.CafeMenuQueryUseCase
import com.breadkun.backend.cafe.application.port.output.CafeMenuCommandPort
import org.springframework.stereotype.Service

@Service
class CafeMenuCommandService(
    private val cafeMenuCommandPort: CafeMenuCommandPort,
    private val cafeMenuQueryUseCase: CafeMenuQueryUseCase
) : CafeMenuCommandUseCase {
    override suspend fun createCafeMenu(userID: String, dto: CafeMenuCreateDTO): CafeMenu {
        return cafeMenuCommandPort.save(dto.toModel(userID))
            .let {
                CafeMenu.fromModel(it)
            }
    }

    override suspend fun updateCafeMenu(cafeMenuId: String, userID: String, dto: CafeMenuUpdateDTO): CafeMenu? {
        return cafeMenuQueryUseCase.findCafeMenuById(cafeMenuId)
            ?.let { existingMenu ->
                cafeMenuCommandPort.update(dto.toModel(cafeMenuId, userID, existingMenu))
            }
            ?.let { updatedMenu ->
                CafeMenu.fromModel(updatedMenu)
            }
    }

    override suspend fun deleteCafeMenuById(cafeMenuId: String): String? {
        return cafeMenuQueryUseCase.findCafeMenuById(cafeMenuId)
            ?.let {
                cafeMenuCommandPort.deleteById(cafeMenuId)
            }
    }
}