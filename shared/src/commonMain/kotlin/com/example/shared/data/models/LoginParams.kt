package com.example.shared.data.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginParams(
    val email: String,
    val password: String
)