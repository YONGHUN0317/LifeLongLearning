package com.src.data.repository

import com.src.data.datasource.remote.LectureApiService
import com.src.data.mapper.Mapper
import com.src.domain.model.LectureEntity
import com.src.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LectureSearchImpl @Inject constructor(
    private val lectureApiService: LectureApiService
) : SearchRepository {
    override fun searchLectures(lctreNm: String): Flow<List<LectureEntity>> = flow {
        val lecturesData = lectureApiService.getSearch(lctreNm)
        val lectures = lecturesData.map { lectureData ->
            Mapper.mapLectureDataToEntity(lectureData)
        }
        emit(lectures)
    }
}