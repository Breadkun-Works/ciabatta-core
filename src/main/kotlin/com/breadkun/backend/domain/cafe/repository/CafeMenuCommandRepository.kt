package com.breadkun.backend.domain.cafe.repository

import com.breadkun.backend.domain.cafe.model.CafeMenu
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository

interface CafeMenuCommandRepository {
    suspend fun save(cafeMenu: CafeMenu): CafeMenu
    suspend fun update(cafeMenu: CafeMenu): CafeMenu
    suspend fun deleteById(cafeMenuId: String): String
}

@Repository
class CafeMenuCommandRepositoryImpl(
    private val cafeMenuCoroutineCrudRepository: CafeMenuCoroutineCrudRepository,
    private val template: R2dbcEntityTemplate
) : CafeMenuCommandRepository {
    override suspend fun save(cafeMenu: CafeMenu): CafeMenu {
        return template.insert(CafeMenu::class.java).using(cafeMenu).awaitSingle()
    }

    override suspend fun update(cafeMenu: CafeMenu): CafeMenu {
        return cafeMenuCoroutineCrudRepository.save(cafeMenu)
    }

    override suspend fun deleteById(cafeMenuId: String): String {
        cafeMenuCoroutineCrudRepository.deleteById(cafeMenuId)
        return cafeMenuId
    }
}