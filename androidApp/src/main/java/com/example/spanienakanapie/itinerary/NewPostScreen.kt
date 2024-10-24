package com.example.spanienakanapie.itinerary

import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@OptIn(ExperimentalMaterial3Api::class, MapboxExperimental::class)
@Composable
fun NewPostScreen(navController: NavController) {

    var titleValue by remember {
        mutableStateOf("")
    }
    var articleContentValue by remember {
        mutableStateOf("")
    }
    var adressValue by remember {
        mutableStateOf("")
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


    LaunchedEffect( true) {
        launch{
            val cameraChange = CameraChangedCallback{cameraChanged ->
                if (true){
                    Log.d("hasjkld", cameraChanged.toString())
                }
            }
        }

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

                OutlinedTextField(value = adressValue,
                    onValueChange = {adressValue = it},
                    enabled = if (adressValue.isEmpty()) false else true,
                    placeholder = {Text(stringResource(R.string.location_address))})
//                Box( modifier = Modifier
//                    .fillMaxWidth()
//                    .height(450.dp)
//
//
//                ){
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(450.dp)
////                            .pointerInput(scrollState){
////                                detectDragGestures(
////                                    onDragStart = {dragState = true},
////                                    onDragEnd = {dragState = false},
////                                ) { _, _->  }
////                            }
//
//
//                    ) {
//                        MapboxMap(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .pointerInput(Unit) {
//                                    detectDragGestures(
//                                        onDragStart = { dragState = true },
//                                        onDragEnd = { dragState = false },
//                                        onDrag = { change, _ ->
////                                            Log.d("dragging", "drag")
//                                            if (dragState) {
//
//                                            } else {
//
//                                            }
//                                        }
//                                    )
//                                },
////                                .pointerInput(Unit) {
////                                    Log.d("drag", "chuj")
////                                    awaitPointerEventScope {
////                                        // we should wait for all new pointer events
////                                        while (true) {
////                                            awaitPointerEvent(pass = PointerEventPass.Initial)
////                                                .changes
////                                                .forEach(PointerInputChange::consume)
////                                        }
////                                    }
////
//////                                    detectVerticalDragGestures(
//////                                        onDragStart = {dragState = true},
//////                                        onDragEnd = {dragState = false},
//////                                        onVerticalDrag = {_, dragAmount ->
//////                                            Log.d("Amount", dragAmount.toString())
//////                                            DragInteraction.Start
//////
//////
//////                                        }
//////                                    )
////                                },
//                            mapViewportState = mapViewportState,
//                            compass = {
//                                Compass(
//                                    modifier = Modifier
//                                        .height(0.dp)
//                                        .width(0.dp)
//                                )
//                            },
//                            scaleBar = {
//                                ScaleBar(
//                                    height = 0.dp,
//                                    primaryColor = Color.Transparent,
//                                    secondaryColor = Color.Transparent,
//                                    textColor = Color.Transparent,
//                                    borderWidth = 0.dp,
//                                    textBorderWidth = 0.dp,
//                                    textSize = 0.sp
//                                )
//                            }
//
//
//                        ) {
//                            MapEffect(Unit) { mapView ->
//                                mapView.location.updateSettings {
//                                    locationPuck = createDefault2DPuck(withBearing = true)
//                                    enabled = true
//                                    puckBearing = PuckBearing.COURSE
//                                    puckBearingEnabled = true
//                                }
//
//                                mapViewportState.transitionToFollowPuckState(
//                                    followPuckViewportStateOptions = FollowPuckViewportStateOptions.Builder()
//                                        .bearing(FollowPuckViewportStateBearing.Constant(0.0))
//                                        .padding(EdgeInsets(200.0 * density, 0.0, 0.0, 0.0))
//                                        .pitch(5.0)
//                                        .zoom(15.0)
//                                        .build(),
//                                ) { success ->
//
//                                }
//
//                            }
//                        }
//
//
//                    }
//
//                }

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