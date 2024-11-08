package com.example.spanienakanapie.itinerary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spanienakanapie.R
import com.example.spanienakanapie.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = stringResource(R.string.places_with_community_posts), color = MaterialTheme.colorScheme.primary) })
        }
    ){
        Column(modifier = Modifier.padding(it)) {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd){
                FloatingActionButton(modifier = Modifier.padding(end = 16.dp),
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(4.dp),
                    onClick = { navController.navigate(Screen.NewPost.route){
                        popUpTo(Screen.Posts.route)
                    } },
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }

        }
    }
}

@Composable
@Preview
fun PostsPreview(){
    PostsScreen(rememberNavController())
}