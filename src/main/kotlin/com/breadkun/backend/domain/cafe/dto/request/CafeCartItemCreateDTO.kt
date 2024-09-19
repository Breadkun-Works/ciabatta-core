package com.breadkun.backend.domain.cafe.dto.request

import com.breadkun.backend.domain.cafe.model.CafeCartItem
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime
import java.util.*

data class CafeCartItemCreateDTO(
    @field:NotBlank(message = "카페 장바구니 ID는 필수입니다.")
    @Schema(description = "카페 장바구니 ID")
    val cafeCartId: String,

    @field:NotBlank(message = "카페 메뉴 ID는 필수입니다.")
    @Schema(description = "카페 메뉴 ID")
    val cafeMenuId: String,

    @field:NotBlank(message = "개수는 필수입니다.")
    @Schema(description = "개수")
    val quantity: Int,

    @field:NotBlank(message = "생성자 ID는 필수입니다.")
    @Schema(description = "생성자 ID")
    val createdById: String,

    @field:NotBlank(message = "생성자 이름은 필수입니다.")
    @Schema(description = "생성자 이름")
    val createdByName: String
) {
    fun toModel(): CafeCartItem {

        return CafeCartItem(
            id = UUID.randomUUID().toString(),
            cafeCartId = cafeCartId,
            cafeMenuId = cafeMenuId,
            quantity = quantity,
            createdAt = LocalDateTime.now(),
            createdById = createdById,
            createdByName = createdByName
        )
    }
}