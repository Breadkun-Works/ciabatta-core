package com.ciabatta.core.infrastructure.persistence.adapter

import com.ciabatta.core.application.port.output.CafeMenuCommandPort
import com.ciabatta.core.infrastructure.persistence.repository.CafeMenuCoroutineCrudRepository
import com.ciabatta.core.infrastructure.persistence.entity.CafeMenuEntity
import org.springframework.stereotype.Repository

@Repository
class CafeMenuCommandAdapter(
    private val cafeMenuCoroutineCrudRepository: CafeMenuCoroutineCrudRepository,
) : CafeMenuCommandPort {
    override suspend fun save(
        cafeMenuEntity: CafeMenuEntity
    ): CafeMenuEntity {
        return cafeMenuCoroutineCrudRepository.save(cafeMenuEntity)
    }

    override suspend fun deleteById(
        cafeMenuId: Long
    ): Long {
        cafeMenuCoroutineCrudRepository.deleteById(cafeMenuId)
        return cafeMenuId
    }
}