package com.breadkun.backend.domain.model

import com.breadkun.backend.domain.model.enums.CafeEnums
import java.time.LocalDateTime

data class CafeCartItemSummary(
    val cafeCartId: String,

    val cafeMenuId: Long,

    val name: String,

    val drinkTemperature: CafeEnums.Menu.Temperature,

    val isPersonalCup: Boolean,

    val price: Int,

    val totalPrice: Int,

    val category: CafeEnums.Menu.Category,

    val imageFilename: String,

    val imageUrl: String,

    val totalQuantity: Int,

    val contributors: List<Contributor>
) {
    companion object {
        fun fromCafeCartItems(
            cafeCartItems: List<CafeCartItem>
        ): CafeCartItemSummary {
            val contributors = cafeCartItems
                .groupBy { it.createdById } // createdById 가 같으면 합산
                .map { (createdById, items) ->
                    Contributor(
                        userId = createdById,
                        userName = items.first().createdByName,
                        quantity = items.sumOf { it.quantity },
                        createdAt = items.last().createdAt
                    )
                }

            val firstItem = cafeCartItems.first()
            val totalQuantity = cafeCartItems.sumOf { it.quantity }

            return CafeCartItemSummary(
                cafeCartId = firstItem.cafeCartId,
                cafeMenuId = firstItem.cafeMenuId,
                name = firstItem.name!!,
                drinkTemperature = firstItem.drinkTemperature!!,
                isPersonalCup = firstItem.isPersonalCup,
                price = firstItem.price!!,
                totalPrice = firstItem.price!! * totalQuantity,
                category = firstItem.category!!,
                imageFilename = firstItem.imageFilename!!,
                imageUrl = firstItem.imageUrl!!,
                totalQuantity = totalQuantity,
                contributors = contributors
            )
        }
    }
}

data class Contributor(
    val userId: String,

    val userName: String,

    val quantity: Int,

    val createdAt: LocalDateTime,
)