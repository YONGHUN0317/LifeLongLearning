package com.src.domain.usecase

import com.src.domain.model.LectureEntity
import com.src.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    operator fun invoke(lctreNm: String): Flow<List<LectureEntity>> = searchRepository.searchLectures(lctreNm)
}