package com.breadkun.backend.cafe.cart.service

import com.breadkun.backend.domain.cafe.cart.dto.request.CafeCartItemCreateDTO
import com.breadkun.backend.cafe.cart.dto.response.CafeCartItemDTO
import com.breadkun.backend.domain.cafe.cart.repository.CafeCartItemCommandRepository
import com.breadkun.backend.domain.cafe.menu.service.CafeMenuQueryService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

fun interface CafeCartItemCommandService {
    suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        dtos: List<CafeCartItemCreateDTO>
    ): List<com.breadkun.backend.cafe.cart.dto.response.CafeCartItemDTO>
}

@Service
class CafeCartItemCommandServiceImpl(
    private val cafeCartItemCommandRepository: CafeCartItemCommandRepository,
    private val cafeCartQueryService: CafeCartQueryService,
    private val cafeMenuQueryService: CafeMenuQueryService
) : CafeCartItemCommandService {
    @Transactional
    override suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        dtos: List<CafeCartItemCreateDTO>
    ): List<com.breadkun.backend.cafe.cart.dto.response.CafeCartItemDTO> {
        validateCartAndMenuExistenceAndLocationMatch(cartId, dtos)

        return cafeCartItemCommandRepository.saveAll(dtos.map { it.toModel(cartId, userUUID) })
            .map {
                com.breadkun.backend.cafe.cart.dto.response.CafeCartItemDTO.fromModel(it)
            }
    }

    private suspend fun validateCartAndMenuExistenceAndLocationMatch(
        cartId: String,
        dtos: List<CafeCartItemCreateDTO>
    ) {
        val cafeCart = cafeCartQueryService.findActiveCafeCartById(cartId)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "CafeCart not found with id: $cartId")

        dtos.forEach { dto ->
            val cafeMenu = cafeMenuQueryService.findCafeMenuById(dto.cafeMenuId)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "CafeMenu not found with id: ${dto.cafeMenuId}")

            require(cafeCart.cafeLocation == cafeMenu.cafeLocation) {
                "CafeCart location and CafeMenu location do not match. " +
                        "Cart location: ${cafeCart.cafeLocation}, " +
                        "Menu location: ${cafeMenu.cafeLocation}"
            }
        }
    }
}