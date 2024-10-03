package com.breadkun.backend.cafe.application.dto.request

import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeCartItemEntity
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.time.LocalDateTime
import java.util.*

data class CafeCartItemCreateDTO(
    @field:NotBlank(message = "카페 메뉴 ID는 필수입니다.")
    @Schema(description = "카페 메뉴 ID")
    val cafeMenuId: String,

    @field:Positive(message = "개수는 양수여야 합니다.")
    @Schema(description = "개수")
    val quantity: Int,

    @field:NotBlank(message = "생성자 이름은 필수입니다.")
    @Schema(description = "생성자 이름")
    val createdByName: String
) {
    fun toModel(cartId: String, userUUID: String): CafeCartItemEntity {

        return CafeCartItemEntity(
            id = UUID.randomUUID().toString(),
            cafeCartId = cartId,
            cafeMenuId = cafeMenuId,
            quantity = quantity,
            createdAt = LocalDateTime.now(),
            createdById = userUUID,
            createdByName = createdByName
        )
    }
}