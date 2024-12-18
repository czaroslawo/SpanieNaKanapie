package com.example.spanienakanapie.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.spanienakanapie.authorization.AuthViewModel
import com.example.spanienakanapie.authorization.LoginScreen
import com.example.spanienakanapie.authorization.RegistrationScreen
import com.example.spanienakanapie.home.HomeScreen
import com.example.spanienakanapie.Post.NewPostScreen
import com.example.spanienakanapie.Post.PickLocationScreen
import com.example.spanienakanapie.Post.PostsScreen
import com.example.spanienakanapie.ui.theme.AppTheme
import com.example.spanienakanapie.viewmodels.MainViewModel
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxExperimental


@OptIn(MapboxExperimental::class)
@ExperimentalLayoutApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun Navigation(viewModel: MainViewModel = viewModel(), mapView: MapView) {


    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route


    when (navBackStackEntry?.destination?.route) {
        Screen.Login.route -> {
            bottomBarState.value = false
        }

        Screen.Register.route -> {
            bottomBarState.value = false
        }

        Screen.Home.route -> {
            bottomBarState.value = true
        }

        Screen.Posts.route ->{
            bottomBarState.value = true
        }
        Screen.NewPost.route ->{
            bottomBarState.value = false
        }
    }




    AppTheme {
            Scaffold(
                contentWindowInsets = WindowInsets(0.dp),
                bottomBar = {

                        if (bottomBarState.value) {
                            BottomNavigationBar(navController)
                        }

                }
            ) {innerPadding->
                NavHost(
                    navController = navController,
                    startDestination = if (state.loggedIn) Screen.Home.route else "auth",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    Log.d("padding", innerPadding.toString())
                    navigation(startDestination = Screen.Login.route, route = "auth") {
                        composable(Screen.Login.route) {
                            bottomBarState.value = false
                            val authViewModel: AuthViewModel = viewModel()
                            LoginScreen(navController) {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo("auth") { inclusive = true }

                                }

                            }
                        }
                        composable(Screen.Register.route) {
                            bottomBarState.value = false
                            RegistrationScreen(navController)

                        }
                    }
                    composable(Screen.Home.route) {
                        bottomBarState.value = true
                        HomeScreen()
                    }
                    composable(Screen.Posts.route) {
                        bottomBarState.value = true
                        PostsScreen(navController)
                    }
                    composable(Screen.NewPost.route){
                        bottomBarState.value = false
                        NewPostScreen(navController)
                    }
                    composable(Screen.PickLocation.route){
                        bottomBarState.value = false
                        PickLocationScreen(navController)
                    }


                }

                }

            }

        }



//Column(
//modifier = Modifier.fillMaxSize()
//) {
//    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(
//                navController = navController,
//                onItemClick = {
//                    navController.navigate(it.route)
//                },
//                items = listOf(
//                    BottomNavItem(
//                        name = "Home",
//                        route = "Home",
//                        icon = Icons.Default.Home
//                    ),
//                    BottomNavItem(
//                        name = "Settings",
//                        route = "Settings",
//                        icon = Icons.Default.Settings
//                    ),
//                ),
//                bottomBarState = bottomBarState
//            )
//        }
//    ) { innerPadding ->
//        Box(
//            modifier = Modifier
//                .padding(innerPadding)
//                .consumeWindowInsets(WindowInsets(top = innerPadding.calculateTopPadding()))
//        ) {



