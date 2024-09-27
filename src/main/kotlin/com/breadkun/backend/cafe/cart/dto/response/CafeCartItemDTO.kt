package com.breadkun.backend.cafe.cart.dto.response

import com.breadkun.backend.domain.cafe.cart.model.CafeCartItem
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class CafeCartItemDTO(
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
        fun fromModel(cafeCartItem: CafeCartItem): CafeCartItemDTO {
            return CafeCartItemDTO(
                id = cafeCartItem.id,
                cafeCartId = cafeCartItem.cafeCartId,
                cafeMenuId = cafeCartItem.cafeMenuId,
                quantity = cafeCartItem.quantity,
                createdAt = cafeCartItem.createdAt,
                createdById = cafeCartItem.createdById,
                createdByName = cafeCartItem.createdByName
            )
        }
    }
}