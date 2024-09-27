package com.breadkun.backend.domain.cafe.menu.service

import com.breadkun.backend.domain.cafe.menu.dto.response.CafeMenuBoardDTO
import com.breadkun.backend.domain.cafe.menu.dto.response.CafeMenuDTO
import com.breadkun.backend.global.common.enums.Location
import com.breadkun.backend.domain.cafe.menu.model.enums.CafeMenuCategory
import com.breadkun.backend.domain.cafe.menu.repository.CafeMenuQueryRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

interface CafeMenuQueryService {
    suspend fun findCafeMenuById(cafeMenuId: String): CafeMenuDTO?
    suspend fun getCafeMenuBoardByOptions(
        cafeLocation: Location?,
        name: String?,
        category: CafeMenuCategory?,
        pageable: Pageable
    ): PageImpl<CafeMenuBoardDTO>
}

@Service
class CafeMenuQueryServiceImpl(
    private val cafeMenuQueryRepository: CafeMenuQueryRepository,
) : CafeMenuQueryService {
    override suspend fun findCafeMenuById(cafeMenuId: String): CafeMenuDTO? {
        return cafeMenuQueryRepository.findById(cafeMenuId)
            ?.let {
                CafeMenuDTO.fromModel(it)
            }
    }

    override suspend fun getCafeMenuBoardByOptions(
        cafeLocation: Location?,
        name: String?,
        category: CafeMenuCategory?,
        pageable: Pageable
    ): PageImpl<CafeMenuBoardDTO> = coroutineScope {
        val totalCountDeferred = if (pageable.isPaged) {
            async { cafeMenuQueryRepository.countByMultipleOptionsWithGrouping(cafeLocation, name, category) }
        } else {
            null
        }

        val cafeMenuListDeferred = async {
            cafeMenuQueryRepository.findByMultipleOptionsWithGrouping(
                cafeLocation, name, category,
                if (pageable.isPaged) pageable.pageNumber else null,
                if (pageable.isPaged) pageable.pageSize else null
            )
        }

        val totalCount = totalCountDeferred?.await() ?: cafeMenuListDeferred.await().size.toLong()
        val cafeMenuList = cafeMenuListDeferred.await()

        PageImpl(
            cafeMenuList,
            if (pageable.isPaged) pageable else PageRequest.of(0, cafeMenuList.size),
            totalCount
        )
    }
}