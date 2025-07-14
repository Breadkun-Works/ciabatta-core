package com.ciabatta.core.dashboard.domain.model

import java.time.LocalDateTime

data class TrendingNews(
    val id: String,
    val title: String,
    val link: String,
    val createdAt: LocalDateTime,
)
