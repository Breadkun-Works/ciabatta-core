package com.breadkun.backend.domain.cafe.service

import com.breadkun.backend.domain.cafe.dto.request.CafeMenuCreateDTO
import com.breadkun.backend.domain.cafe.dto.request.CafeMenuUpdateDTO
import com.breadkun.backend.domain.cafe.model.CafeMenu
import com.breadkun.backend.domain.cafe.repository.CafeMenuCommandRepository
import org.springframework.stereotype.Service

interface CafeMenuCommandService {
    suspend fun createCafeMenu(dto: CafeMenuCreateDTO): CafeMenu
    suspend fun updateCafeMenu(dto: CafeMenuUpdateDTO): CafeMenu?
    suspend fun deleteCafeMenuById(id: String): String?
}

@Service
class CafeMenuCommandServiceImpl(
    private val cafeMenuCommandRepository: CafeMenuCommandRepository,
    private val cafeMenuQueryService: CafeMenuQueryService
) : CafeMenuCommandService {
    override suspend fun createCafeMenu(dto: CafeMenuCreateDTO): CafeMenu {
        val test = dto.toModel()
        return cafeMenuCommandRepository.save(test)
    }

    override suspend fun updateCafeMenu(dto: CafeMenuUpdateDTO): CafeMenu? {
        return cafeMenuQueryService.findCafeMenuById(dto.id)?.let {
            cafeMenuCommandRepository.update(dto.toModel(it))
        }
    }

    override suspend fun deleteCafeMenuById(id: String): String? {
        return cafeMenuQueryService.findCafeMenuById(id)?.let {
            cafeMenuCommandRepository.deleteById(id)
        }
    }
}