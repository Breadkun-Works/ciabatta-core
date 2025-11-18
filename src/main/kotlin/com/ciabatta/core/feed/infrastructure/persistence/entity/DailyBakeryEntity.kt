package com.ciabatta.core.feed.infrastructure.persistence.entity

import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "daily_bakery")
data class DailyBakeryEntity(
    @Id
    val id: String,
    val name: String,
    val imageUrl: String,
    val servedAt: LocalDateTime,
    val createdAt: LocalDateTime,
    val createdById: String,
)
