package com.ciabatta.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@SpringBootApplication
@EnableR2dbcRepositories(
    basePackages = ["com.ciabatta.core.cafe.infrastructure.persistence.repository"],
)
@EnableReactiveMongoRepositories(
    basePackages = ["com.ciabatta.core.feed.infrastructure.persistence.repository"],
)
class CiabattaCoreApplication

fun main(args: Array<String>) {
    runApplication<CiabattaCoreApplication>(*args)
}
