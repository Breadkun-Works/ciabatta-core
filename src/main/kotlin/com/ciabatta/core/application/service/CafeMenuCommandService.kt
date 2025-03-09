package com.ciabatta.core.application.service

import com.ciabatta.core.application.dto.CafeMenuCreateDTO
import com.ciabatta.core.application.dto.CafeMenuUpdateDTO
import com.ciabatta.core.application.mapper.CafeMenuMapper
import com.ciabatta.core.application.port.input.CafeMenuCommandUseCase
import com.ciabatta.core.application.port.input.CafeMenuQueryUseCase
import com.ciabatta.core.application.port.output.CafeMenuCommandPort
import com.ciabatta.core.domain.model.CafeMenu
import org.springframework.stereotype.Service

@Service
class CafeMenuCommandService(
    private val cafeMenuCommandPort: CafeMenuCommandPort,
    private val cafeMenuQueryUseCase: CafeMenuQueryUseCase
) : CafeMenuCommandUseCase {
    override suspend fun createCafeMenu(
        userID: String,
        dto: CafeMenuCreateDTO
    ): CafeMenu {
        val domain = CafeMenuMapper.mapCreateDTOToDomain(userID, dto)
        val entity = CafeMenuMapper.mapDomainToEntity(domain)
        val savedEntity = cafeMenuCommandPort.save(entity)

        return CafeMenuMapper.mapEntityToDomain(savedEntity)
    }

    override suspend fun updateCafeMenu(
        cafeMenuId: Long,
        userID: String,
        dto: CafeMenuUpdateDTO
    ): CafeMenu {
        val existingMenu = cafeMenuQueryUseCase.findCafeMenuById(cafeMenuId)

        val domain = CafeMenuMapper.mapUpdateDTOToDomain(cafeMenuId, userID, existingMenu, dto)
        val entity = CafeMenuMapper.mapDomainToEntity(domain)
        val savedEntity = cafeMenuCommandPort.save(entity)

        return CafeMenuMapper.mapEntityToDomain(savedEntity)
    }

    override suspend fun deleteCafeMenuById(
        cafeMenuId: Long
    ): Long {
        val existingMenu = cafeMenuQueryUseCase.findCafeMenuById(cafeMenuId)

        return cafeMenuCommandPort.deleteById(existingMenu.id!!)
    }
}