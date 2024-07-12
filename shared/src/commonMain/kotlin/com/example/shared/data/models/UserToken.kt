package com.example.shared.data.models

import kotlinx.serialization.Serializable

@Serializable
data class UserToken(
    var authToken: String? = null
)
