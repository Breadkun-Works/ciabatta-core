package com.breadkun.backend.domain.cafe.repository

import org.springframework.stereotype.Repository

fun interface CafeMenuCommandRepository {
    fun saveMenu()
}

@Repository
class CafeMenuCommandRepositoryImpl(
    private val cafeMenuReactiveCrudRepository: CafeMenuReactiveCrudRepository
) : CafeMenuCommandRepository {

}