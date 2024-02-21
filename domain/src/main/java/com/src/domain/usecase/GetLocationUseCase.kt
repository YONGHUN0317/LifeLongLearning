package com.src.domain.usecase

import com.src.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val repository: LocationRepository) {
    operator fun invoke(): Flow<String> = repository.getLocation()
}