package com.breadkun.backend.domain.exception

open class CafeMenuException(message: String) : RuntimeException(message)

class CafeMenuNotFoundException : CafeMenuException("Cafe menu not found.")