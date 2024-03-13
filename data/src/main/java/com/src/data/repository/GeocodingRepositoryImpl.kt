package com.src.data.repository

import android.location.Geocoder
import android.location.Location
import com.src.domain.repository.GeocodingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

/**
 *  지오코딩
 */
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
                    "주소를 못 찾았습니다."
                }
            } catch (e: IOException) {
                "주소 가져오기 오류: ${e.message}"
            }
        }
    }

    override suspend fun getCoordinatesFromAddress(address: String): Location {
        return withContext(Dispatchers.IO) {
            try {
                geocoder.getFromLocationName(address, 1)?.firstOrNull()?.let {
                    Location("").apply {
                        latitude = it.latitude
                        longitude = it.longitude
                    }
                } ?: Location("").apply {
                    latitude = 0.0
                    longitude = 0.0
                }
            } catch (e: IOException) {
                // Log and handle exception appropriately
                Location("").apply {
                    latitude = 0.0
                    longitude = 0.0
                }
            }
        }
    }
}