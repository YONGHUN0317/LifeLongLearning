package com.src.domain.repository

import androidx.paging.PagingSource
import com.src.domain.model.LectureEntity
import com.src.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LectureRepository {
    suspend fun getAllLectures(): Flow<Resource<List<LectureEntity>>>

    fun getLecturesPaged(): PagingSource<Int, LectureEntity>
}