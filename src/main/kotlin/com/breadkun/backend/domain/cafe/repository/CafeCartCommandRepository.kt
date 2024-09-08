package com.breadkun.backend.domain.cafe.repository

import com.breadkun.backend.domain.cafe.model.CafeCart
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository

fun interface CafeCartCommandRepository {
    suspend fun save(cafeCart: CafeCart): CafeCart
}

@Repository
class CafeCartCommandRepositoryImpl(
    private val template: R2dbcEntityTemplate
) : CafeCartCommandRepository {
    override suspend fun save(cafeCart: CafeCart): CafeCart {
        return template.insert(CafeCart::class.java).using(cafeCart).awaitSingle()
    }
}