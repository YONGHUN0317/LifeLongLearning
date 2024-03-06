package com.src.domain.repository

import com.src.domain.model.LectureEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun searchLectures(lctreNm: String): Flow<List<LectureEntity>>
}