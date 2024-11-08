package com.example.shared.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int? = null,
    val title: String,
    val content: String,
    val locationName: String,
    val address: String,
    val city: String
    )
