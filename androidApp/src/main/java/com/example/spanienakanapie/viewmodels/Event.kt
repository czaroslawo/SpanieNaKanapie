package com.example.spanienakanapie.viewmodels

sealed class Event<out T>(
    private val value: T
) {
    data class SnackbarEvent(val message: String) : Event<String>(message)
    data class NavigateEvent(val route: String) : Event<String>(route)

    private var canConsume = true
    fun takeValue() = value

    fun consume(block: (T) -> Unit) {
        if (this.canConsume) {
            canConsume = false
            block(value)
        }
    }
}