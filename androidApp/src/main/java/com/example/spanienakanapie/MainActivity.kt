package com.example.spanienakanapie

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import com.example.spanienakanapie.authorization.LoginScreen
import com.example.spanienakanapie.navigation.Navigation
import com.example.spanienakanapie.ui.theme.AppTheme
import com.example.spanienakanapie.viewmodels.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class,
        ExperimentalAnimationApi::class,
        ExperimentalFoundationApi::class,
        ExperimentalMaterial3Api::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme{
                MainActivityScreen()
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
fun MainActivityScreen(viewModel: MainViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    AppTheme {

        if (!state.loggedIn) {
            Log.d("Login status" ,"Logout")
            LoginScreen(navController = rememberNavController())
        } else {
            Log.d("Login status" ,"Logged in!")
            Navigation()

        }
    }
}

