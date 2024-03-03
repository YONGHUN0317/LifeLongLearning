package com.src.domain.usecase

import com.src.domain.model.LectureEntity
import com.src.domain.repository.FilteredDataRepository
import com.src.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilteredDataUseCase @Inject constructor(
    private val filteredDataRepository: FilteredDataRepository
) {
    operator fun invoke(lctreNm: String): Flow<Resource<List<LectureEntity>>> = filteredDataRepository.filteredData(lctreNm)
}