package com.example.shared.data.models

import kotlinx.serialization.Serializable

@Serializable
data class CityResponse(
    val data: List<City>
)

