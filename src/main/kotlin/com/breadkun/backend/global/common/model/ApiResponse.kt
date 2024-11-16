package com.breadkun.backend.global.common.model

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ApiResponse<T>(
    val success: Boolean,

    val meta: MetaData? = null,

    val data: T? = null,

    val error: ErrorResponse? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class MetaData(
    val totalItems: Int,

    val totalPages: Int? = null,

    val pageSize: Int? = null,

    val currentPage: Int? = null,

    val timestamp: LocalDateTime = LocalDateTime.now()
)

data class ErrorResponse(
    val code: String,

    val message: String
)