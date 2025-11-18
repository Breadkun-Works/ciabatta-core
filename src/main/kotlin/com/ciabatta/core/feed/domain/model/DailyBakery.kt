package com.ciabatta.core.feed.domain.model

import java.time.LocalDateTime

data class DailyBakery(
    val id: String,
    val name: String,
    val imageUrl: String,
    val servedAt: LocalDateTime,
    val createdAt: LocalDateTime,
    val createdById: String,
)
