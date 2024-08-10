package com.example.spanienakanapie.viewmodels

sealed class Event<out T>(
    private val value: T
) {
    data class SnackbarEvent(val message: String) : Event<String>(message)
    data class NavigateEvent(val route: String) : Event<String>(route)

    private var hasBeenHandled = false

    fun takeValue(): T? {
        return if (!hasBeenHandled) {
            hasBeenHandled = true
            value
        } else {
            null
        }
    }

    fun consume(block: (T) -> Unit) {
        takeValue()?.let {
            block(it)
        }
    }
}