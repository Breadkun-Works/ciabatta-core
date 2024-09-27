package com.breadkun.backend.cafe.cart.repository

import com.breadkun.backend.domain.cafe.cart.model.CafeCartItem
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

fun interface CafeCartItemCommandRepository {
    suspend fun saveAll(cafeCartItems: List<CafeCartItem>): List<CafeCartItem>
}

@Repository
class CafeCartItemCommandRepositoryImpl(
    private val template: R2dbcEntityTemplate
) : CafeCartItemCommandRepository {
    override suspend fun saveAll(cafeCartItems: List<CafeCartItem>): List<CafeCartItem> {
        return Flux.fromIterable(cafeCartItems)
            .flatMap { cafeCartItem ->
                template.insert(CafeCartItem::class.java).using(cafeCartItem)
            }
            .collectList()
            .awaitSingle()
    }
}