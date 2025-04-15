package com.ciabatta.core.domain.model

import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.global.enums.GlobalEnums
import java.time.LocalDateTime

data class CafeCart(
    val id: String?,
    val cafeLocation: GlobalEnums.Location,
    val title: String,
    val description: String?,
    val createdAt: LocalDateTime,
    val expiresAt: LocalDateTime,
    val secureShareKey: String?,
    val createdById: String,
) {
    val status: CafeEnums.Cart.Status
        get() {
            val now = LocalDateTime.now()
            return when {
                now.isBefore(expiresAt) -> CafeEnums.Cart.Status.ACTIVE
                else -> CafeEnums.Cart.Status.INACTIVE
            }
        }
}
