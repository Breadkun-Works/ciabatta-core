package com.breadkun.backend.domain.cafe.service

import com.breadkun.backend.domain.cafe.dto.response.CafeCartDTO
import com.breadkun.backend.domain.cafe.repository.CafeCartQueryRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

fun interface CafeCartQueryService {
    suspend fun findActiveCafeCartByCreatedById(createdById: String): List<CafeCartDTO>
}

@Service
class CafeCartQueryServiceImpl(
    private val cafeCartQueryRepository: CafeCartQueryRepository
) : CafeCartQueryService {
    override suspend fun findActiveCafeCartByCreatedById(createdById: String): List<CafeCartDTO> {
        val currentTime = LocalDateTime.now()

        return cafeCartQueryRepository.findActiveByCreatedById(createdById, currentTime)
            .map {
                CafeCartDTO.fromModel(it)
            }
    }
}