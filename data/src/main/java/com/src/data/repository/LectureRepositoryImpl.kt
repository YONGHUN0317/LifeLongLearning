package com.src.data.repository

import androidx.paging.PagingSource
import com.src.data.datasource.remote.LectureApiService
import com.src.data.datasource.remote.LecturePagingSource
import com.src.domain.model.LectureEntity
import com.src.domain.repository.LectureRepository
import javax.inject.Inject

class LectureRepositoryImpl @Inject constructor(
    private val lectureApiService: LectureApiService
) : LectureRepository {


    override fun getLecturesPaged(): PagingSource<Int, LectureEntity> {
        return LecturePagingSource(lectureApiService, query = null)
    }
}
