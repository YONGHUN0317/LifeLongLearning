package com.src.data.api

import com.src.data.model.response.PlacePredictionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesApi {
    @GET("maps/api/place/autocomplete/json")
    suspend fun getPredictions(
        @Query("key") key: String ,
    @Query("types") types: String = "address",
    @Query("input") input: String,
        @Query("components") components: String = "country:KR"
    ): PlacePredictionsResponse

    companion object{
        const val BASE_URL = "https://maps.googleapis.com/"
    }
}