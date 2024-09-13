package com.breadkun.backend.domain.cafe.repository

import com.breadkun.backend.domain.cafe.model.CafeCart
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

fun interface CafeCartQueryRepository {
    suspend fun findActiveByCreatedById(createdById: String, currentTime:LocalDateTime): List<CafeCart>
}

@Repository
class CafeCartQueryRepositoryImpl(
    private val cafeCartCoroutineCrudRepository: CafeCartCoroutineCrudRepository
) : CafeCartQueryRepository {
    override suspend fun findActiveByCreatedById(createdById: String, currentTime:LocalDateTime): List<CafeCart> {
        return cafeCartCoroutineCrudRepository.findByCreatedByIdAndCreatedAtLessThanEqualAndExpiresAtGreaterThanEqual(
            createdById = createdById,
            currentTimeForCreatedAt = currentTime,
            currentTimeForExpiresAt = currentTime
        ).toList()
    }
}