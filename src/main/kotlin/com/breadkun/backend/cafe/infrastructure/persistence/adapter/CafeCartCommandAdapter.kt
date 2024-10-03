package com.breadkun.backend.cafe.infrastructure.persistence.adapter

import com.breadkun.backend.cafe.application.port.output.CafeCartCommandPort
import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeCartEntity
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Repository

@Repository
class CafeCartCommandAdapter(
    private val template: R2dbcEntityTemplate
) : CafeCartCommandPort {
    override suspend fun save(cafeCartEntity: CafeCartEntity): CafeCartEntity {
        return template.insert(CafeCartEntity::class.java).using(cafeCartEntity).awaitSingle()
    }
}