package com.breadkun.backend.cafe.menu.exception

open class CafeMenuException(message: String) : RuntimeException(message)

class CafeMenuNotFoundException : CafeMenuException("Cafe menu not found.")