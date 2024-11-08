package com.example.shared.data.models

import kotlinx.serialization.Serializable

@Serializable
data class City(
    val city: String,
    val locationName: String
)
