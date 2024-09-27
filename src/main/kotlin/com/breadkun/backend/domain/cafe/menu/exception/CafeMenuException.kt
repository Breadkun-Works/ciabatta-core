package com.breadkun.backend.domain.cafe.menu.exception

open class CafeMenuException(message: String) : RuntimeException(message)

class CafeMenuNotFoundException : CafeMenuException("Cafe menu not found.")