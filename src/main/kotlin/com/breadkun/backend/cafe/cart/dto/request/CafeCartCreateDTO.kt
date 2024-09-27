package com.breadkun.backend.cafe.cart.dto.request

import com.breadkun.backend.cafe.cart.model.CafeCart
import com.breadkun.backend.global.common.enums.Location
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime
import java.util.*

data class CafeCartCreateDTO(
    @field:NotNull(message = "카페 위치는 필수입니다.")
    @Schema(description = "카페의 위치", example = "KANGCHON")
    val cafeLocation: Location,

    @field:NotBlank(message = "장바구니 이름은 필수입니다.")
    @field:Size(max = 70, message = "장바구니 이름은 70자 이내여야 합니다.")
    @Schema(description = "장바구니 이름", example = "빵돌이")
    val title: String,

    @field:Size(max = 255, message = "장바구니 설명은 255자 이내여야 합니다.")
    @Schema(description = "장바구니에 대한 설명", example = "빵돌이가 쏜다!")
    val description: String? = null,
) {
    fun toModel(userUUID: String): CafeCart {
        val id = UUID.randomUUID().toString()
        val createdAt = LocalDateTime.now()

        return CafeCart(
            id = id,
            cafeLocation = cafeLocation,
            title = title,
            description = description,
            createdAt = createdAt,
            expiresAt = createdAt.plusHours(3),
            createdById = userUUID,
            sharedUrl = "https://breadkun.com/cafe/carts/$id"
        )
    }
}