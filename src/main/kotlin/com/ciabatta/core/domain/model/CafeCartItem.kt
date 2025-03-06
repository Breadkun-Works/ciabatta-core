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

    val imageUrl: String,

    val createdAt: LocalDateTime,

    val createdById: String,

    val createdByName: String
) {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var drinkName: String? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var drinkPrice: Int? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var drinkTotalPrice: Int? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var drinkCategory: CafeEnums.Menu.Category? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var drinkTemperature: CafeEnums.Menu.Temperature? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var drinkImageFilename: String? = null

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var drinkImageUrl: String? = null

    fun toEntity(
    ): CafeCartItemEntity {
        return CafeCartItemEntity(
            id = id,
            cafeCartId = cafeCartId,
            cafeMenuId = cafeMenuId,
            isPersonalCup = isPersonalCup,
            quantity = quantity,
            imageUrl = imageUrl,
            createdAt = createdAt,
            createdById = createdById,
            createdByName = createdByName
        )
    }

    fun attachDetails(
        cafeMenu: CafeMenu
    ): CafeCartItem {
        return this.apply {
            drinkName = cafeMenu.name
            drinkPrice = if (isPersonalCup) cafeMenu.price else cafeMenu.price + cafeMenu.deposit
            drinkTotalPrice = drinkPrice!! * quantity
            drinkCategory = cafeMenu.category
            drinkTemperature = cafeMenu.drinkTemperature
            drinkImageFilename = cafeMenu.imageFilename
            drinkImageUrl = cafeMenu.imageUrl
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
                imageUrl = cafeCartItemEntity.imageUrl,
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
                imageUrl = cafeCartItemCreateDTO.imageUrl,
                createdAt = LocalDateTime.now(),
                createdById = userUUID,
                createdByName = userName
            )
        }
    }
}