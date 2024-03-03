package com.src.data.repository

import com.src.data.datasource.remote.LectureApiService
import com.src.data.mapper.Mapper
import com.src.domain.model.LectureEntity
import com.src.domain.repository.FilteredDataRepository
import com.src.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LectureFilteredDataImpl @Inject constructor(
    private val lectureApiService: LectureApiService
) : FilteredDataRepository {

    override fun filteredData(lctreNm: String): Flow<Resource<List<LectureEntity>>> = flow {
        try {
            emit(Resource.Loading())

            val lecturesData = lectureApiService.getSearch(lctreNm)

            val lectures = lecturesData.map { lectureData ->
                Mapper.mapLectureDataToEntity(lectureData)
            }

            emit(Resource.Success(lectures))
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }
}