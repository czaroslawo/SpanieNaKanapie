package com.example.spanienakanapie.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirlineStops
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.spanienakanapie.navigation.Screen

sealed class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector,
){
    object Home : BottomNavItem("Home", Screen.Home.route, Icons.Default.Home)
    object Posts : BottomNavItem("Itinerary", Screen.Posts.route, Icons.Default.AirlineStops)
}
