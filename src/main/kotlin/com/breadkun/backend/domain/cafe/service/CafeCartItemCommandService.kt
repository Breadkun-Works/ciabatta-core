package com.breadkun.backend.domain.cafe.service

import com.breadkun.backend.domain.cafe.dto.request.CafeCartItemCreateDTO
import com.breadkun.backend.domain.cafe.dto.response.CafeCartItemDTO
import com.breadkun.backend.domain.cafe.repository.CafeCartItemCommandRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

fun interface CafeCartItemCommandService {
    suspend fun createCafeCartItems(dtos: List<CafeCartItemCreateDTO>): List<CafeCartItemDTO>
}

@Service
class CafeCartItemCommandServiceImpl(
    private val cafeCartItemCommandRepository: CafeCartItemCommandRepository
) : CafeCartItemCommandService {
    @Transactional
    override suspend fun createCafeCartItems(dtos: List<CafeCartItemCreateDTO>): List<CafeCartItemDTO> {
        return cafeCartItemCommandRepository.saveAll(dtos.map { it.toModel() })
            .map {
                CafeCartItemDTO.fromModel(it)
            }
    }
}