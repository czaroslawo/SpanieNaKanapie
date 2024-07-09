package com.example.spanienakanapie.viewmodels

data class AuthState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val event: Event<Any>? = null
)