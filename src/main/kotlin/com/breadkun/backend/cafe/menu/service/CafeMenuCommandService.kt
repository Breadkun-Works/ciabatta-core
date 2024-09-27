package com.breadkun.backend.cafe.menu.service

import com.breadkun.backend.domain.cafe.menu.dto.request.CafeMenuCreateDTO
import com.breadkun.backend.domain.cafe.menu.dto.request.CafeMenuUpdateDTO
import com.breadkun.backend.cafe.menu.dto.response.CafeMenuDTO
import com.breadkun.backend.domain.cafe.menu.repository.CafeMenuCommandRepository
import org.springframework.stereotype.Service

interface CafeMenuCommandService {
    suspend fun createCafeMenu(userID: String, dto: CafeMenuCreateDTO): com.breadkun.backend.cafe.menu.dto.response.CafeMenuDTO
    suspend fun updateCafeMenu(cafeMenuId: String, userID: String, dto: CafeMenuUpdateDTO): com.breadkun.backend.cafe.menu.dto.response.CafeMenuDTO?
    suspend fun deleteCafeMenuById(cafeMenuId: String): String?
}

@Service
class CafeMenuCommandServiceImpl(
    private val cafeMenuCommandRepository: CafeMenuCommandRepository,
    private val cafeMenuQueryService: CafeMenuQueryService
) : CafeMenuCommandService {
    override suspend fun createCafeMenu(userID: String, dto: CafeMenuCreateDTO): com.breadkun.backend.cafe.menu.dto.response.CafeMenuDTO {
        return cafeMenuCommandRepository.save(dto.toModel(userID))
            .let {
                com.breadkun.backend.cafe.menu.dto.response.CafeMenuDTO.fromModel(it)
            }
    }

    override suspend fun updateCafeMenu(cafeMenuId: String, userID: String, dto: CafeMenuUpdateDTO): com.breadkun.backend.cafe.menu.dto.response.CafeMenuDTO? {
        return cafeMenuQueryService.findCafeMenuById(cafeMenuId)
            ?.let { existingMenu ->
                cafeMenuCommandRepository.update(dto.toModel(cafeMenuId, userID, existingMenu))
            }
            ?.let { updatedMenu ->
                com.breadkun.backend.cafe.menu.dto.response.CafeMenuDTO.fromModel(updatedMenu)
            }
    }

    override suspend fun deleteCafeMenuById(cafeMenuId: String): String? {
        return cafeMenuQueryService.findCafeMenuById(cafeMenuId)
            ?.let {
                cafeMenuCommandRepository.deleteById(cafeMenuId)
            }
    }
}