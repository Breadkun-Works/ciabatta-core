package com.breadkun.backend.domain.cafe.service

import com.breadkun.backend.domain.cafe.dto.response.CafeMenuBoardResponseDTO
import com.breadkun.backend.domain.cafe.model.CafeMenu
import com.breadkun.backend.domain.cafe.model.enum.CafeLocation
import com.breadkun.backend.domain.cafe.model.enum.CafeMenuCategory
import com.breadkun.backend.domain.cafe.repository.CafeMenuQueryRepository
import com.breadkun.backend.global.common.util.PaginationUtils
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.data.domain.PageImpl
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
        val pageable = PaginationUtils.validatePagination(page, size)

        val totalCountDeferred =
            async { cafeMenuQueryRepository.countByMultipleOptionsWithGrouping(cafeLocation, name, category) }
        val cafeMenuListDeferred =
            async {
                cafeMenuQueryRepository.findByMultipleOptionsWithGrouping(
                    cafeLocation,
                    name,
                    category,
                    if (pageable.isPaged) pageable.pageNumber else null,
                    if (pageable.isPaged) pageable.pageSize else null
                )
            }

        val totalCount = totalCountDeferred.await()
        val cafeMenuList = cafeMenuListDeferred.await()

        val safePageable = PaginationUtils.safePageable(pageable, totalCount)

        PageImpl(cafeMenuList, safePageable, totalCount)
    }
}