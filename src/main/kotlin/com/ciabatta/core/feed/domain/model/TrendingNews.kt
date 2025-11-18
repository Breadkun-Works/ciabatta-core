package com.ciabatta.core.feed.domain.model

import java.time.LocalDateTime

data class TrendingNews(
    val id: String,
    val title: String,
    val link: String,
    val createdAt: LocalDateTime,
)
