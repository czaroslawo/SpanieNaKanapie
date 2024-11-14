package com.example.spanienakanapie.Post

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shared.data.models.City
import com.example.shared.data.models.Post
import com.example.spanienakanapie.R
import com.example.spanienakanapie.navigation.Screen

fun LazyListScope.Section(
    city: City,
    posts: List<Post>,
    isExpanded: Boolean,
    onClick: () -> Unit
){


    item{
        CityItem(city = city.city,
            isExpended = isExpanded,
            onItemClicked = onClick)
        }

        items(posts.size){
            AnimatedVisibility(visible = isExpanded,
                enter = fadeIn(animationSpec = tween(300)) +
                        expandVertically(animationSpec = tween(300)),
                exit = fadeOut(animationSpec = tween(300)) +
                        shrinkVertically(animationSpec = tween(300))
                ) {
                    PostItem(
                        title = posts[it].title,
                        locationName = posts[it].locationName
                    )
                }
    }

    }




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(navController: NavController,
                viewModel: PostViewModel = viewModel(LocalContext.current as ComponentActivity)
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val cities: List<City> = state.cities
    val posts: List<Post> = state.posts
    var postsByCity by rememberSaveable {
        mutableStateOf<Map<String, List<Post>>>(emptyMap())
    }
    var expandedItems by rememberSaveable {
        mutableStateOf<Map<Int, Boolean>>(emptyMap())
    }
    var index = 0
    LaunchedEffect(true) {
        viewModel.getCities()
        expandedItems = cities.mapIndexed { index, _ -> index to false }.toMap()
    }

    LaunchedEffect(posts) {
        val city = state.city
        if (city != null) {
            postsByCity = postsByCity.toMutableMap().apply {
                this[city] = posts
            }
        }
    }






    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = stringResource(R.string.places_with_community_posts), color = MaterialTheme.colorScheme.primary) })
        }
    ){
        Column(modifier = Modifier.padding(it)) {
            LazyColumn {
                cities.onEachIndexed { index, city ->
                    Section(
                        city = city,
                        posts = postsByCity[city.city] ?: posts,
                        isExpanded = expandedItems[index] ?: false,
                        onClick = {

                            viewModel.setCity(city.city)
                            viewModel.getPosts()

                            Log.d("state", state.toString())
                            expandedItems = expandedItems.toMutableMap().apply {
                                this[index] = !(this[index] ?: false)
                            }
                        }
                    )
                }


//                items(cities.size) { city ->
//                    CityItem(city = cities[city].city, isExpended = expandedItems[city] ?: false,
//                        onItemClicked = {
//                            viewModel.setCity(cities[city].city)
//
//                            expandedItems = expandedItems.toMutableMap().apply {
//                                this[city] = !(this[city] ?: false)
//                            }
//                            viewModel.getPosts()
//                        })
//
//                    Log.d("ExpandedItem", expandedItems[city].toString())
//                    if (expandedItems[city] == true) {
//                        LazyColumn(Modifier.height(height.dp)) {
//                            items(posts.size){post ->
//                                PostItem(title = posts[post].title,
//                                    locationName = posts[post].locationName)
//                            }
//                        }
//                    }
//
//                }
                }
            }
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomEnd){
                FloatingActionButton(modifier = Modifier.padding(end = 16.dp, bottom = 12.dp),
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


@Composable
@Preview
fun PostsPreview(){
    PostsScreen(rememberNavController())
}