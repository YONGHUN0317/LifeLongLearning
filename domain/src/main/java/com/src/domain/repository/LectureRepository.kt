package com.src.domain.repository

import androidx.paging.PagingSource
import com.src.domain.model.LectureEntity
import kotlinx.coroutines.flow.Flow

interface LectureRepository {
    fun getLecturesPaged(): PagingSource<Int, LectureEntity>
}