package com.src.domain.repository


interface GeocodingRepository {
    suspend fun getAddressFromLocation(latitude: Double, longitude: Double): String
}