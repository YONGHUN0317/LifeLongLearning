package com.src.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.src.domain.model.Lecture
import com.src.domain.repository.LectureRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLecturesUseCase  @Inject constructor(
    private val lectureRepository: LectureRepository
) {
    fun execute(): Flow<PagingData<Lecture>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, // Define your page size
                enablePlaceholders = false
            ),
            pagingSourceFactory = { lectureRepository.getLecturesPaged() }
        ).flow
    }
}