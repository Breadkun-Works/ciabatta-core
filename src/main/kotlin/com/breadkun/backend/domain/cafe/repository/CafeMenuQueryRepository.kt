package com.breadkun.backend.domain.cafe.repository

import com.breadkun.backend.domain.cafe.model.CafeMenu
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

fun interface CafeMenuQueryRepository {
    fun findByCategory(category: String): Mono<CafeMenu>
}

@Repository
class CafeMenuQueryRepositoryImpl(
    private val cafeMenuReactiveCrudRepository: CafeMenuReactiveCrudRepository
) : CafeMenuQueryRepository {
    override fun findByCategory(category: String): Mono<CafeMenu> {
        return cafeMenuReactiveCrudRepository.findCafeMenuByCategory(category)
    }
}