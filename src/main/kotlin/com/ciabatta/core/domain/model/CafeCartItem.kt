package com.ciabatta.core.domain.model

import com.ciabatta.core.domain.model.enums.CafeEnums
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
    val createdByName: String,
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

    fun attachDetails(cafeMenu: CafeMenu): CafeCartItem =
        this.apply {
            drinkName = cafeMenu.name
            drinkPrice = if (isPersonalCup) cafeMenu.price else cafeMenu.price + cafeMenu.deposit
            drinkTotalPrice = drinkPrice!! * quantity
            drinkCategory = cafeMenu.category
            drinkTemperature = cafeMenu.drinkTemperature
            drinkImageFilename = cafeMenu.imageFilename
            drinkImageUrl = cafeMenu.imageUrl
        }
}
