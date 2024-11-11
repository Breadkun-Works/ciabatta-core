package com.breadkun.backend.cafe.domain.model

import com.breadkun.backend.cafe.application.dto.CafeMenuCreateDTO
import com.breadkun.backend.cafe.application.dto.CafeMenuUpdateDTO
import com.breadkun.backend.cafe.infrastructure.persistence.entity.CafeMenuEntity
import com.breadkun.backend.global.common.enums.Location
import com.breadkun.backend.cafe.domain.model.enums.CafeMenuCategory
import com.breadkun.backend.cafe.domain.model.enums.DrinkTemperature
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.*

data class CafeMenu(
    @Schema(description = "메뉴의 고유 ID")
    val id: String,

    @Schema(description = "카페의 위치")
    val cafeLocation: Location,

    @Schema(description = "메뉴의 이름")
    val name: String,

    @Schema(description = "메뉴 가격")
    val price: Int,

    @Schema(description = "컵 보증금")
    val deposit: Int,

    @Schema(description = "메뉴의 카테고리")
    val category: CafeMenuCategory,

    @Schema(description = "음료의 온도")
    val drinkTemperature: DrinkTemperature,

    @Schema(description = "판매 가능 여부")
    val available: Boolean,

    @Schema(description = "메뉴에 대한 설명")
    val description: String?,

    @Schema(description = "이미지 파일 이름")
    val imageFilename: String?,

    @Schema(description = "이미지의 URL")
    val imageUrl: String?,

    @Schema(description = "생성일")
    val createdAt: LocalDateTime,

    @Schema(description = "생성자 ID")
    val createdById: String,

    @Schema(description = "수정자 ID")
    val updatedAt: LocalDateTime?,

    @Schema(description = "수정일")
    val updatedById: String?
) {
    fun toEntity(): CafeMenuEntity {
        return CafeMenuEntity(
            id = id,
            cafeLocation = cafeLocation,
            name = name,
            price = price,
            deposit = deposit,
            category = category,
            drinkTemperature = drinkTemperature,
            available = available,
            description = description,
            imageFilename = imageFilename,
            imageUrl = imageUrl,
            createdAt = createdAt,
            createdById = createdById,
            updatedAt = updatedAt,
            updatedById = updatedById
        )
    }

    companion object {
        fun fromEntity(cafeMenuEntity: CafeMenuEntity): CafeMenu {
            return CafeMenu(
                id = cafeMenuEntity.id,
                cafeLocation = cafeMenuEntity.cafeLocation,
                name = cafeMenuEntity.name,
                price = cafeMenuEntity.price,
                deposit = cafeMenuEntity.deposit,
                category = cafeMenuEntity.category,
                drinkTemperature = cafeMenuEntity.drinkTemperature,
                available = cafeMenuEntity.available,
                description = cafeMenuEntity.description,
                imageFilename = cafeMenuEntity.imageFilename,
                imageUrl = cafeMenuEntity.imageUrl,
                createdAt = cafeMenuEntity.createdAt,
                createdById = cafeMenuEntity.createdById,
                updatedAt = cafeMenuEntity.updatedAt,
                updatedById = cafeMenuEntity.updatedById
            )
        }

        fun fromCreateDTO(
            userID: String,
            cafeMenuCreateDTO: CafeMenuCreateDTO
        ): CafeMenu {
            return CafeMenu(
                id = UUID.randomUUID().toString(),
                cafeLocation = cafeMenuCreateDTO.cafeLocation,
                name = cafeMenuCreateDTO.name,
                price = cafeMenuCreateDTO.price,
                deposit = cafeMenuCreateDTO.deposit,
                category = cafeMenuCreateDTO.category,
                drinkTemperature = cafeMenuCreateDTO.drinkTemperature,
                available = true,
                description = cafeMenuCreateDTO.description,
                imageFilename = cafeMenuCreateDTO.imageFilename,
                imageUrl = cafeMenuCreateDTO.imageUrl,
                createdAt = LocalDateTime.now(),
                createdById = userID,
                updatedAt = null,
                updatedById = null
            )
        }

        fun fromUpdateDTO(
            cafeMenuId: String,
            userID: String,
            existingMenu: CafeMenu,
            cafeMenuUpdateDTO: CafeMenuUpdateDTO
        ): CafeMenu {
            return CafeMenu(
                id = cafeMenuId,
                cafeLocation = cafeMenuUpdateDTO.cafeLocation ?: existingMenu.cafeLocation,
                name = cafeMenuUpdateDTO.name ?: existingMenu.name,
                price = cafeMenuUpdateDTO.price ?: existingMenu.price,
                deposit = cafeMenuUpdateDTO.deposit ?: existingMenu.deposit,
                category = cafeMenuUpdateDTO.category ?: existingMenu.category,
                drinkTemperature = cafeMenuUpdateDTO.drinkTemperature ?: existingMenu.drinkTemperature,
                available = cafeMenuUpdateDTO.available ?: existingMenu.available,
                description = cafeMenuUpdateDTO.description ?: existingMenu.description,
                imageFilename = cafeMenuUpdateDTO.imageFilename ?: existingMenu.imageFilename,
                imageUrl = cafeMenuUpdateDTO.imageUrl ?: existingMenu.imageUrl,
                createdAt = existingMenu.createdAt,
                createdById = existingMenu.createdById,
                updatedAt = LocalDateTime.now(),
                updatedById = userID
            )
        }
    }
}