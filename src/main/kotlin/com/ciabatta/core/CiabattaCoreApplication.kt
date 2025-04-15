package com.ciabatta.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CiabattaCoreApplication

fun main(args: Array<String>) {
    runApplication<CiabattaCoreApplication>(*args)
}
