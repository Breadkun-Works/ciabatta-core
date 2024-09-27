package com.breadkun.backend.cafe.cart.exception

open class CafeCartException(message: String) : RuntimeException(message)

class CafeCartNotFoundException : com.breadkun.backend.cafe.cart.exception.CafeCartException("Cafe cart not found.")
class CafeCartItemNotFoundException : com.breadkun.backend.cafe.cart.exception.CafeCartException("Cafe cart item not found.")