package com.breadkun.backend.cafe.menu.repository

import com.breadkun.backend.cafe.menu.model.CafeMenu
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeMenuCoroutineCrudRepository : CoroutineCrudRepository<com.breadkun.backend.cafe.menu.model.CafeMenu, String> {}