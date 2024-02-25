package com.src.data.repository

import androidx.paging.PagingSource
import com.src.data.datasource.remote.LectureApiService
import com.src.data.datasource.remote.LecturePagingSource
import com.src.data.mapper.Mapper
import com.src.data.model.LectureData
import com.src.domain.model.LectureEntity
import com.src.domain.repository.LectureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LectureRepositoryImpl @Inject constructor(
    private val lectureApiService: LectureApiService
) : LectureRepository {

    override fun getAllLectures(): Flow<List<LectureEntity>> {
        return lectureApiService.getAllLectures().map { lectureDataList ->
            lectureDataList.map { lectureData ->
                Mapper.mapLectureDataToEntity(lectureData)
            }
        }
    }

    override fun getLecturesPaged(): PagingSource<Int, LectureEntity> {
        return LecturePagingSource(lectureApiService)
    }
}
