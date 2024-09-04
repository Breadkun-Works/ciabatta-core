package com.breadkun.backend.domain.cafe.exception

open class CafeDomainException(message: String) : RuntimeException(message)

class CafeMenuNotFoundException : CafeDomainException("Cafe menu not found.")
class CafeCartNotFoundException : CafeDomainException("Cafe cart not found.")
class CafeCartItemNotFoundException : CafeDomainException("Cafe cart item not found.")