package com.example.spanienakanapie.itinerary

data class PostState(
    val placeName: String? = null,
    val placeAddress: String? = null,
    val content: String = "",
    val title: String = "",
) {
}