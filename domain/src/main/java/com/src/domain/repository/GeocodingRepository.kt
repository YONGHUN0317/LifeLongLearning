package com.src.domain.repository

import android.location.Location


interface GeocodingRepository {
    suspend fun getAddressFromLocation(latitude: Double, longitude: Double): String
    suspend fun getCoordinatesFromAddress(address: String): Location
}