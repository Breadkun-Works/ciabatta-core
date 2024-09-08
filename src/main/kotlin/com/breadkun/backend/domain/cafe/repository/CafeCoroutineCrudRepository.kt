package com.breadkun.backend.domain.cafe.repository

import com.breadkun.backend.domain.cafe.model.CafeCart
import com.breadkun.backend.domain.cafe.model.CafeMenu
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeMenuCoroutineCrudRepository : CoroutineCrudRepository<CafeMenu, String> {
}

interface CafeCartCoroutineCrudRepository : CoroutineCrudRepository<CafeCart, String> {
}