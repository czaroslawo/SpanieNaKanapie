package com.example.spanienakanapie.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.spanienakanapie.model.BottomNavItem
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
) {
    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Itinerary
    )
    val backstackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = backstackEntry.value?.destination

    NavigationBar {
        screens.forEach{ screen ->
            AddItem(item = screen, currentDestination = currentDestination, navController = navController)
            
        }
    }

}


@Composable
fun RowScope.AddItem(
    item: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavController
){
    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
        onClick = {navController.navigate(item.route)},
        icon = {item.icon},
        label = {item.title})
}

