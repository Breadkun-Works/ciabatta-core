package com.breadkun.backend.domain.cafe.menu.repository

import com.breadkun.backend.domain.cafe.menu.model.CafeMenu
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeMenuCoroutineCrudRepository : CoroutineCrudRepository<CafeMenu, String> {}