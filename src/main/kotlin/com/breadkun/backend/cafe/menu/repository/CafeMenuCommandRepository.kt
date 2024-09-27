package com.breadkun.backend.cafe.menu.repository

import com.breadkun.backend.cafe.menu.model.CafeMenu
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository

interface CafeMenuCommandRepository {
    suspend fun save(cafeMenu: com.breadkun.backend.cafe.menu.model.CafeMenu): com.breadkun.backend.cafe.menu.model.CafeMenu
    suspend fun update(cafeMenu: com.breadkun.backend.cafe.menu.model.CafeMenu): com.breadkun.backend.cafe.menu.model.CafeMenu
    suspend fun deleteById(cafeMenuId: String): String
}

@Repository
class CafeMenuCommandRepositoryImpl(
    private val cafeMenuCoroutineCrudRepository: CafeMenuCoroutineCrudRepository,
    private val template: R2dbcEntityTemplate
) : CafeMenuCommandRepository {
    override suspend fun save(cafeMenu: com.breadkun.backend.cafe.menu.model.CafeMenu): com.breadkun.backend.cafe.menu.model.CafeMenu {
        return template.insert(com.breadkun.backend.cafe.menu.model.CafeMenu::class.java).using(cafeMenu).awaitSingle()
    }

    override suspend fun update(cafeMenu: com.breadkun.backend.cafe.menu.model.CafeMenu): com.breadkun.backend.cafe.menu.model.CafeMenu {
        return cafeMenuCoroutineCrudRepository.save(cafeMenu)
    }

    override suspend fun deleteById(cafeMenuId: String): String {
        cafeMenuCoroutineCrudRepository.deleteById(cafeMenuId)
        return cafeMenuId
    }
}