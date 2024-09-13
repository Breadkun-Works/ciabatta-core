package com.breadkun.backend.domain.cafe.dto.request

import com.breadkun.backend.domain.cafe.model.CafeCart
import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime
import java.util.*

data class CafeCartCreateDTO(
    @field:NotNull(message = "카페 위치는 필수입니다.")
    @Schema(description = "카페의 위치", example = "KANGCHON")
    val cafeLocation: CafeLocation,

    @field:NotBlank(message = "장바구니 이름은 필수입니다.")
    @field:Size(max = 70, message = "장바구니 이름은 70자 이내여야 합니다.")
    @Schema(description = "장바구니 이름", example = "빵돌이")
    val title: String,

    @field:Size(max = 255, message = "장바구니 설명은 255자 이내여야 합니다.")
    @Schema(description = "장바구니에 대한 설명", example = "빵돌이가 쏜다!")
    val description: String? = null,

    @field:NotBlank(message = "생성자 ID는 필수입니다.")
    @Schema(description = "생성자 ID")
    val createdById: String
) {
    fun toModel(): CafeCart {
        val id = UUID.randomUUID().toString()
        val createdAt = LocalDateTime.now()

        return CafeCart(
            id = id,
            cafeLocation = cafeLocation,
            title = title,
            description = description,
            createdAt = createdAt,
            expiresAt = createdAt.plusHours(3),
            createdById = createdById,
            sharedUrl = "https://breadkun.com/cafe/carts/$id"
        )
    }
}