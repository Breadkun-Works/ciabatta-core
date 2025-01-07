package com.breadkun.backend.infrastructure.persistence.adapter

import com.breadkun.backend.application.port.output.CafeCartCommandPort
import com.breadkun.backend.infrastructure.persistence.entity.CafeCartEntity
import com.breadkun.backend.infrastructure.persistence.repository.CafeCartCoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
class CafeCartCommandAdapter(
    private val cafeCartCoroutineCrudRepository: CafeCartCoroutineCrudRepository
) : CafeCartCommandPort {
    override suspend fun save(
        cafeCartEntity: CafeCartEntity
    ): CafeCartEntity {
        return cafeCartCoroutineCrudRepository.save(cafeCartEntity) // test
    }

    override suspend fun deleteAll(
        ids: List<String>
    ) {
        return cafeCartCoroutineCrudRepository.deleteAllByIds(ids)
    }
}