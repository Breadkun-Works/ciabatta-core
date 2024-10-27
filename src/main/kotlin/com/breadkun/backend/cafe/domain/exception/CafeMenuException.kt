package com.breadkun.backend.cafe.domain.exception

open class CafeMenuException(message: String) : RuntimeException(message)

class CafeMenuNotFoundException : CafeMenuException("Cafe menu not found.")