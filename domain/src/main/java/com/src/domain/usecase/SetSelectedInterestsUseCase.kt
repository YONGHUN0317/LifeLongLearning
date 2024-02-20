package com.src.domain.usecase

import com.src.domain.repository.InterestRepository
import javax.inject.Inject

class SetSelectedInterestsUseCase @Inject constructor(
    private val repository: InterestRepository
) {
    suspend operator fun invoke(selected: Set<String>) = repository.setSelectedInterests(selected)
}