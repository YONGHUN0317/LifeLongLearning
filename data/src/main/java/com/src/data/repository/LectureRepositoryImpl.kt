package com.src.data.repository

import androidx.paging.PagingSource
import com.src.data.datasource.remote.LectureApiService
import com.src.data.datasource.remote.LecturePagingSource
import com.src.data.mapper.Mapper
import com.src.domain.model.LectureEntity
import com.src.domain.repository.LectureRepository
import com.src.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LectureRepositoryImpl @Inject constructor(
    private val lectureApiService: LectureApiService
) : LectureRepository {

    override suspend fun getAllLectures(): Flow<Resource<List<LectureEntity>>> {
        return flow {
            try {
                emit(Resource.Loading()) // Emit loading state
                val page = 1
                val size = 20
                val lecturesData = lectureApiService.getLecturesByPage(page, size)
                val lectures = lecturesData.map { lectureData ->
                    Mapper.mapLectureDataToEntity(lectureData)
                }
                emit(Resource.Success(lectures)) // Emit success state with data
            } catch (e: Exception) {
                emit(Resource.Error("데이터를 가져오지 못했습니다.", null)) // Emit error state
            }
        }
    }

    override fun getLecturesPaged(): PagingSource<Int, LectureEntity> {
        return LecturePagingSource(lectureApiService)
    }
}
