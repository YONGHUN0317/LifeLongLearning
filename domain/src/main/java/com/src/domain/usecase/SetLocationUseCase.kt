package com.src.domain.usecase

import com.src.domain.repository.LocationRepository
import javax.inject.Inject

class SetLocationUseCase @Inject constructor(private val repository: LocationRepository) {
    suspend operator fun invoke(location: String, latitude: Double, longitude: Double) = repository.setLocation(location, latitude, longitude)
}