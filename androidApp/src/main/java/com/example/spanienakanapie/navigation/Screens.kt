package com.example.spanienakanapie.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Itinerary : Screen("itinerary")
    object NewPost : Screen("newPost")
    object PickLocation : Screen("pickLocation")
}
