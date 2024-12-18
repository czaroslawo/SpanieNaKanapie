package com.example.spanienakanapie


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.spanienakanapie.authorization.LoginScreen
import com.example.spanienakanapie.navigation.Navigation
import com.example.spanienakanapie.ui.theme.AppTheme
import com.example.spanienakanapie.viewmodels.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.spanienakanapie.navigation.Screen
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import androidx.compose.material3.Surface
import com.mapbox.android.core.permissions.PermissionsManager
import android.Manifest



@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class,
        ExperimentalAnimationApi::class,
        ExperimentalFoundationApi::class,
        ExperimentalMaterial3Api::class
    )
    lateinit var permissionsManager: PermissionsManager

    @OptIn(ExperimentalLayoutApi::class, ExperimentalAnimationApi::class,
        ExperimentalMaterial3Api::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
        )
        super.onCreate(savedInstanceState)

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                } else -> {
                // No location access granted.
            }
            }
        }

        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))



        //WindowCompat.setDecorFitsSystemWindows(window, false)
        val mapView = MapView(this)
        mapView.mapboxMap.setCamera(
            CameraOptions.Builder()
                .center(Point.fromLngLat(-98.0, 39.5))
                .pitch(0.0)
                .zoom(2.0)
                .bearing(0.0)
                .build()
        )



        setContent {
                AppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Navigation(mapView = mapView)
                    }

                }

        }
    }

}





@Composable
@OptIn(ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
fun MainActivityScreen(viewModel: MainViewModel = viewModel(), mapView: MapView) {
    val state by viewModel.state.collectAsState()

    AppTheme {
        if (!state.loggedIn) {
            LoginScreen(navController = rememberNavController(), onClickToRegister = {})
        } else {
            Scaffold(
                bottomBar = {
                    if (!shouldShowBottomBar(navController = rememberNavController())) {
//                        BottomNavigationBar(rememberNavController())

                    }
                }
            ) {
                Log.d("padding value", Modifier.padding(it).toString())
                Column (modifier = Modifier.padding(it)){
//                    Navigation()

                }

            }

        }


    }
}

@Composable
fun shouldShowBottomBar(navController: NavHostController): Boolean {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val routesWithBottomBar = listOf(Screen.Login.route,
                                    Screen.Register.route)
    Log.d("shouldShow", currentRoute.toString())
    return currentRoute in routesWithBottomBar
}

