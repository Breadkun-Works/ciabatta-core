package com.breadkun.backend.cafe.domain.model

import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeCartEntity
import com.breadkun.backend.global.common.enums.Location
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class CafeCart(
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
        fun fromModel(cafeCartEntity: CafeCartEntity): CafeCart {
            return CafeCart(
                id = cafeCartEntity.id,
                cafeLocation = cafeCartEntity.cafeLocation,
                title = cafeCartEntity.title,
                description = cafeCartEntity.description,
                createdAt = cafeCartEntity.createdAt,
                expiresAt = cafeCartEntity.expiresAt,
                createdById = cafeCartEntity.createdById,
                sharedUrl = cafeCartEntity.sharedUrl
            )
        }
    }
}