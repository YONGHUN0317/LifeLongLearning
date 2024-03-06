package com.src.data.repository

import androidx.paging.PagingSource
import com.src.data.datasource.remote.LectureApiService
import com.src.data.datasource.remote.LecturePagingSource
import com.src.data.mapper.Mapper
import com.src.domain.model.LectureEntity
import com.src.domain.repository.LectureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LectureRepositoryImpl @Inject constructor(
    private val lectureApiService: LectureApiService
) : LectureRepository {

    override suspend fun getAllLectures(): Flow<List<LectureEntity>> {
        return flow {
            val page = 1
            val size = 20
            val lectures = lectureApiService.getLecturesByPage(page, size)
            emit(lectures.map { lectureData ->
                Mapper.mapLectureDataToEntity(lectureData)
            })
        }
    }

    override fun getLecturesPaged(): PagingSource<Int, LectureEntity> {
        return LecturePagingSource(lectureApiService)
    }
}
