package com.breadkun.backend.application.service

import com.breadkun.backend.application.dto.CafeMenuUpdateDTO
import com.breadkun.backend.domain.model.CafeMenu
import org.springframework.stereotype.Service

@Service
class CafeMenuCommandService(
    private val cafeMenuCommandPort: com.breadkun.backend.application.port.output.CafeMenuCommandPort,
    private val cafeMenuQueryUseCase: com.breadkun.backend.application.port.input.CafeMenuQueryUseCase
) : com.breadkun.backend.application.port.input.CafeMenuCommandUseCase {
    override suspend fun createCafeMenu(userID: String, dto: com.breadkun.backend.application.dto.CafeMenuCreateDTO): CafeMenu {
        return cafeMenuCommandPort.save(CafeMenu.fromCreateDTO(userID, dto).toEntity())
            .let {
                CafeMenu.fromEntity(it)
            }
    }

    override suspend fun updateCafeMenu(cafeMenuId: String, userID: String, dto: CafeMenuUpdateDTO): CafeMenu? {
        return cafeMenuQueryUseCase.findCafeMenuById(cafeMenuId)
            ?.let { existingMenu ->
                cafeMenuCommandPort.update(CafeMenu.fromUpdateDTO(cafeMenuId, userID, existingMenu, dto).toEntity())
            }
            ?.let { updatedMenu ->
                CafeMenu.fromEntity(updatedMenu)
            }
    }

    override suspend fun deleteCafeMenuById(cafeMenuId: String): String? {
        return cafeMenuQueryUseCase.findCafeMenuById(cafeMenuId)
            ?.let {
                cafeMenuCommandPort.deleteById(cafeMenuId)
            }
    }
}