package com.example.spanienakanapie.itinerary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@OptIn(MapboxExperimental::class)
@Composable
fun PickLocationScreen(){
    val mapViewportState = rememberMapViewportState(){
        setCameraOptions(){
            pitch(0.0)
            zoom(14.0)

        }
    }
    val density = LocalDensity.current.density

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp),
    ) {
        Column(modifier = Modifier.padding(it)) {
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
        }
    }
}