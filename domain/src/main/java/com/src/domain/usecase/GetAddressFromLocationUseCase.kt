package com.src.domain.usecase

import com.src.domain.repository.GeocodingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAddressFromLocationUseCase @Inject constructor(private val repository: GeocodingRepository) {
    suspend operator fun invoke(latitude: Double, longitude: Double): Flow<String> = flow {
        val address = repository.getAddressFromLocation(latitude, longitude)
        emit(address)
    }
}