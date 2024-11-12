package com.example.spanienakanapie.Post

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spanienakanapie.R
import com.example.spanienakanapie.navigation.Screen
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spanienakanapie.utils.Dialog

@OptIn(ExperimentalMaterial3Api::class, MapboxExperimental::class)
@Composable
fun NewPostScreen(navController: NavController,
                  viewModel: PostViewModel = viewModel(LocalContext.current as ComponentActivity)) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    var titleValue by remember {
        mutableStateOf("")
    }
    var articleContentValue by remember {
        mutableStateOf("")
    }
    var addressValue by remember {
        mutableStateOf<String?>("")
    }
    val mapViewportState = rememberMapViewportState() {
        setCameraOptions() {
            pitch(0.0)
            zoom(14.0)

        }
    }
    val openDialog = remember { mutableStateOf(false) }


    val scrollState = rememberScrollState()


    Scaffold(topBar = {
        CenterAlignedTopAppBar(navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, "")
            }
        },
            title = {
                Text(
                    text = stringResource(R.string.create_article),
                    color = MaterialTheme.colorScheme.primary
                )
            },
        )
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .imePadding()


        ) {
            Column(modifier = Modifier
                .padding(horizontal = 10.dp)
                .verticalScroll(
                    scrollState,
                )

            ) {
                OutlinedTextField(
                    value = state.title,
                    onValueChange = { viewModel.setTitle(it) },
                    label = { Text(text = stringResource(R.string.article_title)) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = state.content, onValueChange = { viewModel.setContent(it) },
                    maxLines = 1000,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                        .padding(vertical = 16.dp)
                )



                Button(onClick = {navController.navigate(Screen.PickLocation.route)},
                    modifier = Modifier.fillMaxWidth()
                        ) {
                    Icon(Icons.Default.LocationOn, "")
                    Text(text = stringResource(R.string.pick_the_location))
                }

                Log.i("State: placeAddres", state.placeAddress.toString())
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    value = state.placeName?: "",
                    onValueChange = { viewModel.setPlaceName(it) },
                    enabled = state.placeName != null,
                    placeholder = { Text(stringResource(R.string.location_name)) })

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    value = state.placeAddress ?: "",
                    onValueChange = { viewModel.setPlaceAddress(it) },
                    enabled = state.placeAddress != null,
                    placeholder = { Text(stringResource(R.string.location_address)) })

                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd) {
                    Button(onClick = { openDialog.value = true },
                        modifier = Modifier.padding(vertical = 16.dp)) {
                        Text(text = stringResource(R.string.publish))
                    }
                }
                if (openDialog.value) {
                    Dialog(
                        onDismissRequest = { openDialog.value = false },
                        onConfirmation = {
                            viewModel.addPost()
                            openDialog.value = false
                            navController.navigate(Screen.Posts.route)
                        },
                        dialogTitle = stringResource(R.string.are_you_sure),
                        dialogText = stringResource(R.string.Every_user_will_see),
                        icon = Icons.Default.Biotech
                    )
                }
            }


            }
        }
    }





@Composable
@Preview
fun NewPostPreview(){
    NewPostScreen(rememberNavController())
}