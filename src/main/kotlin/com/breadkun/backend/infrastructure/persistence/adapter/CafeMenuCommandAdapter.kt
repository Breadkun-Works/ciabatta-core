package com.breadkun.backend.infrastructure.persistence.adapter

import com.breadkun.backend.application.port.output.CafeMenuCommandPort
import com.breadkun.backend.infrastructure.persistence.repository.CafeMenuCoroutineCrudRepository
import com.breadkun.backend.infrastructure.persistence.entity.CafeMenuEntity
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