package com.breadkun.backend.domain.cafe.service

import com.breadkun.backend.domain.cafe.model.CafeMenu
import com.breadkun.backend.domain.cafe.repository.CafeMenuQueryRepository
import org.springframework.stereotype.Service

fun interface CafeMenuQueryService {
    suspend fun findCafeMenuById(id: String): CafeMenu?
}

@Service
class CafeMenuQueryServiceImpl(
    private val cafeMenuQueryRepository: CafeMenuQueryRepository,
) : CafeMenuQueryService {
    override suspend fun findCafeMenuById(id: String): CafeMenu? {
        return cafeMenuQueryRepository.findById(id)
    }
}