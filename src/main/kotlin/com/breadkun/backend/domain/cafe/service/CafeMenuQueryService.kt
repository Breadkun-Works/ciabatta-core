package com.breadkun.backend.domain.cafe.service

import com.breadkun.backend.domain.cafe.dto.response.CafeMenuBoardResponseDTO
import com.breadkun.backend.domain.cafe.model.CafeMenu
import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import com.breadkun.backend.domain.cafe.model.enum.CafeMenuCategory
import com.breadkun.backend.domain.cafe.repository.CafeMenuQueryRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

interface CafeMenuQueryService {
    suspend fun findCafeMenuById(id: String): CafeMenu?
    suspend fun getCafeMenuBoardByOptions(
        cafeLocation: CafeLocation?,
        name: String?,
        category: CafeMenuCategory?,
        page: Int?,
        size: Int?
    ): PageImpl<CafeMenuBoardResponseDTO>
}

@Service
class CafeMenuQueryServiceImpl(
    private val cafeMenuQueryRepository: CafeMenuQueryRepository,
) : CafeMenuQueryService {
    override suspend fun findCafeMenuById(id: String): CafeMenu? {
        return cafeMenuQueryRepository.findById(id)
    }

    override suspend fun getCafeMenuBoardByOptions(
        cafeLocation: CafeLocation?,
        name: String?,
        category: CafeMenuCategory?,
        page: Int?,
        size: Int?
    ): PageImpl<CafeMenuBoardResponseDTO> = coroutineScope {
        val totalCountDeferred =
            async { cafeMenuQueryRepository.countByMultipleOptionsWithGrouping(cafeLocation, name, category) }
        val cafeMenuListDeferred =
            async { cafeMenuQueryRepository.findByMultipleOptionsWithPaging(cafeLocation, name, category, page, size) }

        val totalCount = totalCountDeferred.await()
        val cafeMenuList = cafeMenuListDeferred.await()

        val data = CafeMenuBoardResponseDTO.fromModel(cafeMenuList)

        PageImpl(data, PageRequest.of(page ?: 0, size ?: data.size), totalCount)
    }
}