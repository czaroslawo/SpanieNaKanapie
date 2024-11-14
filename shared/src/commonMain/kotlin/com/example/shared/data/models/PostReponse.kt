package com.example.shared.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PostReponse(
    val data: List<Post>
)
