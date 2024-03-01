package com.src.data.repository

import android.util.Log
import androidx.paging.PagingSource
import com.src.data.datasource.remote.LectureApiService
import com.src.data.datasource.remote.LecturePagingSource
import com.src.data.mapper.Mapper
import com.src.domain.model.LectureEntity
import com.src.domain.repository.LectureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LectureRepositoryImpl @Inject constructor(
    private val lectureApiService: LectureApiService
) : LectureRepository {

    override suspend fun getAllLectures(): Flow<List<LectureEntity>> {
        return flow {
            val lectures = lectureApiService.getAllLectures()
            emit(lectures.map { lectureData ->
                Mapper.mapLectureDataToEntity(lectureData)
            })
        }
    }

    /*override suspend fun getAllLectures(): Flow<List<LectureEntity>> {
        return lectureApiService.getAllLectures()
            .map { lectureDataList ->
                Log.d("LectureRepositoryImpl", "Mapping data: $lectureDataList")
                lectureDataList.map { lectureData ->
                    Mapper.mapLectureDataToEntity(lectureData)
                }
            }
            .catch { exception ->
                // 네트워크 요청 실패 시 로그 남기기
                Log.e("LectureRepositoryImpl", "Error fetching lectures: ${exception.message}", exception)
                throw exception // 에러를 다시 throw 하여 상위로 전파할 수 있습니다.
            }

    }*/

    override fun getLecturesPaged(): PagingSource<Int, LectureEntity> {
        return LecturePagingSource(lectureApiService)
    }
}
