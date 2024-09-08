package com.breadkun.backend.domain.cafe.service

import com.breadkun.backend.domain.cafe.dto.request.CafeCartCreateDTO
import com.breadkun.backend.domain.cafe.dto.response.CafeCartDTO
import com.breadkun.backend.domain.cafe.repository.CafeCartCommandRepository
import org.springframework.stereotype.Service

fun interface CafeCartCommandService {
    suspend fun createCafeCart(dto: CafeCartCreateDTO): CafeCartDTO
}

@Service
class CafeCartCommandServiceImpl(
    private val cafeCartCommandRepository: CafeCartCommandRepository
) : CafeCartCommandService {
    override suspend fun createCafeCart(dto: CafeCartCreateDTO): CafeCartDTO {
        return cafeCartCommandRepository.save(dto.toModel())
            .let {
                CafeCartDTO.fromModel(it)
            }
    }
}