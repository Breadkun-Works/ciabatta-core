package com.ciabatta.core.domain.model

import com.ciabatta.core.application.dto.CafeCartItemCreateDTO
import com.ciabatta.core.domain.model.enums.CafeEnums
import com.ciabatta.core.infrastructure.persistence.entity.CafeCartItemEntity
import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

data class CafeCartItem(
    val id: String?,

    val cafeCartId: String,

    val cafeMenuId: Long,

    val isPersonalCup: Boolean,

    val quantity: Int,

    val createdAt: LocalDateTime,

    val createdById: String,

    val createdByName: String
) {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var name: String? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var price: Int? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var totalPrice: Int? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var category: CafeEnums.Menu.Category? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var drinkTemperature: CafeEnums.Menu.Temperature? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var imageFilename: String? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var imageUrl: String? = null

    fun toEntity(
    ): CafeCartItemEntity {
        return CafeCartItemEntity(
            id = id,
            cafeCartId = cafeCartId,
            cafeMenuId = cafeMenuId,
            isPersonalCup = isPersonalCup,
            quantity = quantity,
            createdAt = createdAt,
            createdById = createdById,
            createdByName = createdByName
        )
    }

    fun attachDetails(
        cafeMenu: CafeMenu
    ): CafeCartItem {
        return this.apply {
            name = cafeMenu.name
            price = if (isPersonalCup) cafeMenu.price else cafeMenu.price + cafeMenu.deposit
            totalPrice = price!! * quantity
            category = cafeMenu.category
            drinkTemperature = cafeMenu.drinkTemperature
            imageFilename = cafeMenu.imageFilename
            imageUrl = cafeMenu.imageUrl
        }
    }

    companion object {
        fun fromEntity(
            cafeCartItemEntity: CafeCartItemEntity
        ): CafeCartItem {
            return CafeCartItem(
                id = cafeCartItemEntity.id,
                cafeCartId = cafeCartItemEntity.cafeCartId,
                cafeMenuId = cafeCartItemEntity.cafeMenuId,
                isPersonalCup = cafeCartItemEntity.isPersonalCup,
                quantity = cafeCartItemEntity.quantity,
                createdAt = cafeCartItemEntity.createdAt,
                createdById = cafeCartItemEntity.createdById,
                createdByName = cafeCartItemEntity.createdByName
            )
        }

        fun fromCreateDTO(
            cartId: String,
            userUUID: String,
            userName: String,
            cafeCartItemCreateDTO: CafeCartItemCreateDTO
        ): CafeCartItem {
            return CafeCartItem(
                id = null,
                cafeCartId = cartId,
                cafeMenuId = cafeCartItemCreateDTO.cafeMenuId,
                isPersonalCup = cafeCartItemCreateDTO.isPersonalCup,
                quantity = cafeCartItemCreateDTO.quantity,
                createdAt = LocalDateTime.now(),
                createdById = userUUID,
                createdByName = userName
            )
        }
    }
}