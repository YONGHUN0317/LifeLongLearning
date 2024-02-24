package com.src.domain.repository

import androidx.paging.PagingSource
import com.src.domain.model.Lecture
import kotlinx.coroutines.flow.Flow

interface LectureRepository {
    fun getAllLectures(): Flow<List<Lecture>>

    fun getLecturesPaged(): PagingSource<Int, Lecture>
}