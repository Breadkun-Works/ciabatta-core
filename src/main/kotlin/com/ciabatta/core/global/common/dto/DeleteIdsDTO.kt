package com.ciabatta.core.global.common.dto

import jakarta.validation.constraints.NotEmpty

data class DeleteIdsDTO(
    @field:NotEmpty(message = "삭제할 ID 목록은 비어 있을 수 없습니다.")
    val ids: List<String>
)
