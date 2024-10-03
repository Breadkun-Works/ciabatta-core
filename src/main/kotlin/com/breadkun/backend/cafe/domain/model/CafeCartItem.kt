package com.breadkun.backend.cafe.domain.model

import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeCartItemEntity
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class CafeCartItem(
    @Schema(description = "장바구니 아이템의 고유 ID")
    val id: String,

    @Schema(description = "장바구니의 고유 ID")
    val cafeCartId: String,

    @Schema(description = "메뉴의 고유 ID")
    val cafeMenuId: String,

    @Schema(description = "개수")
    val quantity: Int,

    @Schema(description = "생성일")
    val createdAt: LocalDateTime,

    @Schema(description = "생성자 ID")
    val createdById: String,

    @Schema(description = "생성자 이름")
    val createdByName: String
) {
    companion object {
        fun fromModel(cafeCartItemEntity: CafeCartItemEntity): CafeCartItem {
            return CafeCartItem(
                id = cafeCartItemEntity.id,
                cafeCartId = cafeCartItemEntity.cafeCartId,
                cafeMenuId = cafeCartItemEntity.cafeMenuId,
                quantity = cafeCartItemEntity.quantity,
                createdAt = cafeCartItemEntity.createdAt,
                createdById = cafeCartItemEntity.createdById,
                createdByName = cafeCartItemEntity.createdByName
            )
        }
    }
}