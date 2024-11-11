package com.breadkun.backend.infrastructure.persistence.repository

import com.breadkun.backend.infrastructure.persistence.entity.CafeMenuEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface CafeMenuCoroutineCrudRepository : CoroutineCrudRepository<CafeMenuEntity, String> {}