package com.src.data.model.response

data class PlacePredictionsResponse(
    val predictions: ArrayList<PlacePrediction>
)

data class PlacePredictionTerm(
    val offset: Int,
    val value: String
)

data class PlacePrediction(
    val description: String,
    val terms: List<PlacePredictionTerm>
)

