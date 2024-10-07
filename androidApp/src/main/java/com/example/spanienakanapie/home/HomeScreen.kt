package com.example.spanienakanapie.home

import android.graphics.Rect
import android.view.RoundedCorner
import android.view.View
import android.view.Window
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.spanienakanapie.R
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateBearing
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateOptions
import com.mapbox.search.autocomplete.PlaceAutocomplete
import java.util.Calendar


@MapboxExperimental
@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {

    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    val mapViewportState = rememberMapViewportState(){
        setCameraOptions(){
            pitch(0.0)
            zoom(14.0)

        }
    }
    val density = LocalDensity.current.density
    val mapboxAccesToken = stringResource(R.string.mapbox_access_token)
    val placeAutocomplete = PlaceAutocomplete.create()
    var place by remember{
        mutableStateOf("")
    }
//    val rectangle = Rect()
//    val window: Window = getWindow()
//    window.decorView.getWindowVisibleDisplayFrame(rectangle)
//    val statusBarHeight: Int = rectangle.top
//    val contentViewTop = window.findViewById<View>(Window.ID_ANDROID_CONTENT).top
//    val titleBarHeight = contentViewTop - statusBarHeight
//
//    val statusBar = WindowInsets.statusBars.getTop(density = density)


    LaunchedEffect(true){
        val response = placeAutocomplete.suggestions(place)
    }
    
    

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp),
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MapboxMap(
                    modifier = Modifier.fillMaxSize(),
                    mapViewportState = mapViewportState,
                    compass = {
                        Compass(modifier = Modifier
                            .height(0.dp)
                            .width(0.dp))
                    },
                    scaleBar = {
                        ScaleBar(height = 0.dp,
                                    primaryColor = Color.Transparent,
                                    secondaryColor = Color.Transparent,
                                    textColor = Color.Transparent,
                                    borderWidth = 0.dp,
                                    textBorderWidth = 0.dp,
                            textSize = 0.sp
                        )
                    }


                ) {
                    MapEffect(Unit) { mapView ->
                        mapView.location.updateSettings {
                            locationPuck = createDefault2DPuck(withBearing = true)
                            enabled = true
                            puckBearing = PuckBearing.COURSE
                            puckBearingEnabled = true
                        }

                        mapViewportState.transitionToFollowPuckState(
                            followPuckViewportStateOptions = FollowPuckViewportStateOptions.Builder()
                                .bearing(FollowPuckViewportStateBearing.Constant(0.0))
                                .padding(EdgeInsets(200.0 * density, 0.0, 0.0, 0.0))
                                .pitch(5.0)
                                .zoom(15.0)
                                .build(),
                        ) { success ->

                        }

                    }
                }
                TextField(modifier = Modifier
                    .fillMaxWidth()
                    .systemBarsPadding()
                    .padding(horizontal = 10.dp),
//                    .border(
//                        0.dp,
//                        MaterialTheme.colorScheme.bac,
////                        shape = RoundedCornerShape(30.dp)
//                    ),
                    //.clip(RoundedCornerShape(30.dp)),
                    value = place,
                    onValueChange = {place = it },
                    placeholder = { Text(text = stringResource(R.string.find_place))},
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent),
                    shape = RoundedCornerShape(30.dp)
                )
            }})
}
@Preview
@MapboxExperimental
@ExperimentalMaterial3Api
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}