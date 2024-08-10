package com.example.spanienakanapie.authorization

import com.example.spanienakanapie.viewmodels.Event

data class AuthState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val event: Event<Any>? = null
)