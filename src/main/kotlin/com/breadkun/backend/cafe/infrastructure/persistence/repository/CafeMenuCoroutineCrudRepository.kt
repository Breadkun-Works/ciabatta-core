package com.breadkun.backend.cafe.infrastructure.persistence.repository

import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeMenuEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeMenuCoroutineCrudRepository : CoroutineCrudRepository<CafeMenuEntity, String> {}