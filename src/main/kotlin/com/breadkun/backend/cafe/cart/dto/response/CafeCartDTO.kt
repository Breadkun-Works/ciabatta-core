package com.breadkun.backend.cafe.cart.dto.response

import com.breadkun.backend.domain.cafe.cart.model.CafeCart
import com.breadkun.backend.global.common.enums.Location
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class CafeCartDTO(
    @Schema(description = "장바구니의 고유 ID")
    val id: String,

    @Schema(description = "카페의 위치")
    val cafeLocation: Location,

    @Schema(description = "장바구니의 이름")
    val title: String,

    @Schema(description = "장바구니에 대한 설명")
    val description: String?,

    @Schema(description = "생성일")
    val createdAt: LocalDateTime,

    @Schema(description = "종료일")
    val expiresAt: LocalDateTime,

    @Schema(description = "생성자 ID")
    val createdById: String,

    @Schema(description = "공유 URL")
    val sharedUrl: String
) {
    companion object {
        fun fromModel(cafeCart: CafeCart): CafeCartDTO {
            return CafeCartDTO(
                id = cafeCart.id,
                cafeLocation = cafeCart.cafeLocation,
                title = cafeCart.title,
                description = cafeCart.description,
                createdAt = cafeCart.createdAt,
                expiresAt = cafeCart.expiresAt,
                createdById = cafeCart.createdById,
                sharedUrl = cafeCart.sharedUrl
            )
        }
    }
}