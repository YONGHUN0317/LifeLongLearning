package com.src.data.repository

import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class GeocodingRepository @Inject constructor(private val geocoder: Geocoder) {

    suspend fun getAddressFromLocation(latLng: LatLng): String {
        return withContext(Dispatchers.IO) {
            try {
                val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
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

