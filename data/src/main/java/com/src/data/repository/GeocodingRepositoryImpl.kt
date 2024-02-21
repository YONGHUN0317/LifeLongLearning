package com.src.data.repository

import android.location.Geocoder
import com.src.domain.repository.GeocodingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GeocodingRepositoryImpl @Inject constructor(
    private val geocoder: Geocoder
) : GeocodingRepository {
    override suspend fun getAddressFromLocation(latitude: Double, longitude: Double): String {
        return withContext(Dispatchers.IO) {
            try {
                val addresses = geocoder.getFromLocation(latitude, longitude, 1)
                if (addresses!!.isNotEmpty()) {
                    addresses[0].getAddressLine(0)
                } else {
                    "Address not found"
                }
            } catch (e: IOException) {
                "Error getting address: ${e.message}"
            }
        }
    }
}