package com.ciabatta.core.cafe.application.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CafeCartItemCreateDTO(
    @field:Min(1, message = "Cafe menu ID is required")
    val cafeMenuId: Long,
    @field:NotNull(message = "Personal cup usage is required")
    val isPersonalCup: Boolean,
    @field:Min(1, message = "Quantity must be at least 1")
    val quantity: Int,
    @field:NotBlank(message = "Cart item image is required")
    @field:Size(max = 255, message = "Cart item image must be within 255 characters")
    @field:Pattern( // Url은 항상 지정된 스토리지 경로에 위치해야 함
        regexp = "^https://ax40oxk5pwva\\.objectstorage\\.ap-chuncheon-1\\.oci\\.customer-oci\\.com" +
            "/p/ggBWzbdG5d85FYMMw0ox2fgAuITiYbFVcSJRa2f4is_rp69RHi1H3-HSMMUpU1el" +
            "/n/ax40oxk5pwva/b/BreadFiles/o/images/cafe/cart/character/.*\$",
        message = "URL must always be located in the designated storage path."
    )
    val imageUrl: String,
)
