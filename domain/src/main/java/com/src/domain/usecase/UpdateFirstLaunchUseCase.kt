package com.src.domain.usecase

import com.src.domain.repository.PreferencesRepository
import javax.inject.Inject

class UpdateFirstLaunchUseCase @Inject constructor(
    private val repository: PreferencesRepository
) {
    suspend operator fun invoke(isFirstLaunch: Boolean) = repository.updateFirstLaunch(isFirstLaunch)
}