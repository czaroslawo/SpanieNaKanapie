package com.example.spanienakanapie.Post

import com.example.shared.data.models.City
import com.example.shared.data.models.Post
import com.example.spanienakanapie.viewmodels.Event

data class PostState(
    val placeName: String? = null,
    val placeAddress: String? = null,
    val content: String = "",
    val title: String = "",
    val city: String? = "",
    val cities: List<City> = emptyList(),
    val posts: List<Post> = emptyList(),
    val event: Event<Any>? = null
) {
}