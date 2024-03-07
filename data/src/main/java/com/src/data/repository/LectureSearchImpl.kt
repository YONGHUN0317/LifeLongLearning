package com.src.data.repository

import androidx.paging.PagingSource
import com.src.data.datasource.remote.LectureApiService
import com.src.data.datasource.remote.LecturePagingSource
import com.src.data.mapper.Mapper
import com.src.domain.model.LectureEntity
import com.src.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LectureSearchImpl @Inject constructor(
    private val lectureApiService: LectureApiService
) : SearchRepository {

    override fun getSearchLectures(query: String): PagingSource<Int, LectureEntity> {
        return LecturePagingSource(lectureApiService, query)
    }
}