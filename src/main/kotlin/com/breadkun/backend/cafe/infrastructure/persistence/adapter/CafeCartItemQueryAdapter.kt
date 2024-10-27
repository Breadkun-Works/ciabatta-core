package com.breadkun.backend.cafe.infrastructure.persistence.adapter

import com.breadkun.backend.cafe.application.port.output.CafeCartItemQueryPort
import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeCartItemEntity
import com.breadkun.backend.cafe.infrastructure.persistence.repository.CafeCartItemCoroutineCrudRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Repository

@Repository
class CafeCartItemQueryAdapter(
    private val cafeCartItemCoroutineCrudRepository: CafeCartItemCoroutineCrudRepository
) : CafeCartItemQueryPort {
    override suspend fun findByCafeCartId(cafeCartId: String): List<CafeCartItemEntity> {
        return cafeCartItemCoroutineCrudRepository.findByCafeCartId(cafeCartId).toList()
    }
}