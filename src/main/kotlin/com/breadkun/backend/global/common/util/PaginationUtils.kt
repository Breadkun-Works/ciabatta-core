package com.breadkun.backend.global.common.util

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

object PaginationUtils {
    fun validatePagination(page: Int?, size: Int?): Pageable {
        val isValidPage = page != null && page >= 0
        val isValidSize = size != null && size > 0

        return if (isValidPage && isValidSize) {
            PageRequest.of(page!!, size!!)
        } else {
            Pageable.unpaged()
        }
    }

    fun safePageable(pageable: Pageable, totalCount: Long): Pageable {
        return if (pageable.isPaged) {
            pageable
        } else {
            PageRequest.of(0, totalCount.toInt())  // 전체 데이터 반환 시 기본값 설정
        }
    }
}