package com.src.domain.repository

import com.src.domain.model.LectureEntity
import com.src.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FilteredDataRepository {
    fun filteredData(lctreNm: String): Flow<Resource<List<LectureEntity>>>
}