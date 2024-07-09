package com.example.spanienakanapie.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.spanienakanapie.authorization.LoginScreen
import com.example.spanienakanapie.model.BottomNavItem


@ExperimentalLayoutApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun Navigation() {


    val navController = rememberNavController()

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()


    when (navBackStackEntry?.destination?.route) {
        Screen.Login.route ->{
            bottomBarState.value = false
        }
    }

Column(
modifier = Modifier.fillMaxSize()
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                },
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = "Home",
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Settings",
                        route = "Settings",
                        icon = Icons.Default.Settings
                    ),
                ),
                bottomBarState = bottomBarState
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(WindowInsets(top = innerPadding.calculateTopPadding()))
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Login.route
            ) {
                composable(Screen.Login.route) {
                    LoginScreen(navController)
                }



            }
        }
    }
}
}
