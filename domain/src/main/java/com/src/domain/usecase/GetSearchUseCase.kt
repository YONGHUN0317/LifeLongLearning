package com.src.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.src.domain.model.LectureEntity
import com.src.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    operator fun invoke(query: String): Flow<PagingData<LectureEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { searchRepository.getSearchLectures(query) }
        ).flow
    }
}