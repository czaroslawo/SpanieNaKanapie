package com.example.spanienakanapie.itinerary

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.spanienakanapie.R
import com.example.spanienakanapie.navigation.Screen
import com.mapbox.android.gestures.AndroidGesturesManager
import com.mapbox.maps.CameraChanged
import com.mapbox.maps.CameraChangedCallback
import com.mapbox.maps.CameraManager
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.CameraState
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.logD
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.gestures.GesturesPlugin
import com.mapbox.maps.plugin.gestures.generated.GesturesSettings
import com.mapbox.maps.plugin.gestures.gestures
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateBearing
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateOptions
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel

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

    val density = LocalDensity.current.density
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                // By returning Offset.Zero, we indicate that we are consuming the scroll and not passing it up.
                return Offset.Zero
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                // Again, return Offset.Zero to consume any remaining scroll distance.
                return available
            }
        }
    }
    val scrollState = rememberScrollState()
    var dragState by remember{
        mutableStateOf(false)
    }





    Scaffold(topBar = {
        CenterAlignedTopAppBar(navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.ArrowBack, "")
            }
        },
            title = {
                Text(
                    text = stringResource(R.string.create_article),
                    color = MaterialTheme.colorScheme.primary
                )
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Check, "")
                }
            }
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
                    enabled = if (!dragState) true else false
                )

            ) {
                OutlinedTextField(
                    value = titleValue,
                    onValueChange = { titleValue = it },
                    label = { Text(text = stringResource(R.string.article_title)) },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = articleContentValue, onValueChange = { articleContentValue = it },
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
                        .padding(vertical= 16.dp),
                    value = state.placeAddress ?: "",
                    onValueChange = { viewModel.setPlaceAddress(it) },
                    enabled = !state.placeAddress.isNullOrEmpty(),
                    placeholder = { Text(stringResource(R.string.location_address)) })

                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd) {
                    Button(onClick = { /*TODO*/ },
                        modifier = Modifier.padding(vertical = 16.dp)) {
                        Text(text = stringResource(id = R.string.confirm))
                    }
                }


            }
        }
    }
}

fun Modifier.scrollEnabled(enabled: Boolean) = nestedScroll(
    connection = object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            // When scrolling is enabled, allow the scroll offset to pass through.
            return if (enabled) Offset.Zero else available
        }

        override suspend fun onPreFling(available: Velocity): Velocity {
            // When scrolling is enabled, allow flinging to happen.
            return if (enabled) Velocity.Zero else available
        }
    }
)



@Composable
@Preview
fun NewPostPreview(){
    NewPostScreen(rememberNavController())
}