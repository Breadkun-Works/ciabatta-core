package com.ciabatta.core.global.dto

import jakarta.validation.constraints.NotEmpty

data class DeleteIdsDTO(
    @field:NotEmpty(message = "The list of IDs to delete cannot be empty")
    val ids: List<String>,
)
