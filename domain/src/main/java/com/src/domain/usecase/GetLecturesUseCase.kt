package com.src.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.src.domain.model.LectureEntity
import com.src.domain.repository.LectureRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLecturesUseCase @Inject constructor(
    private val lectureRepository: LectureRepository
) {
    operator fun invoke(): Flow<PagingData<LectureEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { lectureRepository.getLecturesPaged() }
        ).flow
    }
}