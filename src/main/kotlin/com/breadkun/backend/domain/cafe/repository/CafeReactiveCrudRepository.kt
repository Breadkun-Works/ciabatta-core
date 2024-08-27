package com.breadkun.backend.domain.cafe.repository

import com.breadkun.backend.domain.cafe.model.CafeCart
import com.breadkun.backend.domain.cafe.model.CafeCartItem
import com.breadkun.backend.domain.cafe.model.CafeMenu
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface CafeMenuReactiveCrudRepository : ReactiveCrudRepository<CafeMenu, Int> {
    fun findCafeMenuByCategory(category: String): Mono<CafeMenu>
}

interface CafeCartReactiveCrudRepository : ReactiveCrudRepository<CafeCart, Int> {
}

interface CafeCartItemReactiveCrudRepository : ReactiveCrudRepository<CafeCartItem, Int> {
}