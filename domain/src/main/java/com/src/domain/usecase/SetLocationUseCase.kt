package com.src.domain.usecase

import com.src.domain.repository.LocationRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SetLocationUseCase @Inject constructor(private val repository: LocationRepository) {
    suspend operator fun invoke(location: String, latitude: String, longitude: String) = repository.setLocation(location, latitude, longitude)
}