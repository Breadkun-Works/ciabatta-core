package com.ciabatta.core.dashboard.infrastructure.persistence.entity

import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "trending_news")
data class TrendingNewsEntity(
    @Id
    val id: String,
    val title: String,
    val link: String,
    val createdAt: LocalDateTime,
)
