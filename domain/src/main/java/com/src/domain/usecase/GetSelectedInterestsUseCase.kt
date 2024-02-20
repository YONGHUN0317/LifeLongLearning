package com.src.domain.usecase

import com.src.domain.repository.InterestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectedInterestsUseCase @Inject constructor(
    private val repository: InterestRepository
) {
    suspend operator fun invoke(): Flow<Set<String>> = repository.getSelectedInterests()
}