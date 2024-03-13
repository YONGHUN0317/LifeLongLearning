package com.src.domain.usecase

import android.location.Location
import com.src.domain.repository.GeocodingRepository
import javax.inject.Inject

class GetCoordinatesFromAddressUseCase@Inject constructor(private val repository: GeocodingRepository) {
    suspend operator fun invoke(address: String): Location = repository.getCoordinatesFromAddress(address)
}