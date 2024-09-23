package com.breadkun.backend.domain.cafe.service

import com.breadkun.backend.domain.cafe.dto.request.CafeCartItemCreateDTO
import com.breadkun.backend.domain.cafe.dto.response.CafeCartItemDTO
import com.breadkun.backend.domain.cafe.repository.CafeCartItemCommandRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

fun interface CafeCartItemCommandService {
    suspend fun createCafeCartItems(
        cartId: String,
        userUUID: String,
        dtos: List<CafeCartItemCreateDTO>
    ): List<CafeCartItemDTO>
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
    ): List<CafeCartItemDTO> {
        validateCartAndMenuExistenceAndLocationMatch(cartId, dtos)

        return cafeCartItemCommandRepository.saveAll(dtos.map { it.toModel(cartId, userUUID) })
            .map {
                CafeCartItemDTO.fromModel(it)
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