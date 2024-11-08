package com.example.spanienakanapie.itinerary

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.VectorDrawable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import com.example.spanienakanapie.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mapbox.geojson.Point
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.extension.compose.MapEffect
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.logD
import com.mapbox.maps.plugin.PuckBearing
import com.mapbox.maps.plugin.annotation.AnnotationManagerImpl
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.locationcomponent.createDefault2DPuck
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateBearing
import com.mapbox.maps.plugin.viewport.data.FollowPuckViewportStateOptions
import com.mapbox.search.ReverseGeoOptions
import com.mapbox.search.SearchEngine
import com.mapbox.search.SearchEngineSettings
import com.mapbox.search.autocomplete.PlaceAutocomplete
import com.mapbox.search.autocomplete.PlaceAutocompleteSuggestion
import kotlinx.coroutines.delay
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.shared.data.models.PickPlace
import com.example.spanienakanapie.authorization.AuthViewModel
import com.example.spanienakanapie.navigation.Screen
import com.mapbox.maps.Image
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.style.sources.generated.rememberImageSourceState
import com.mapbox.search.ResponseInfo
import com.mapbox.search.SearchCallback
import com.mapbox.search.result.SearchResult

@OptIn(MapboxExperimental::class)
@Composable
fun PickLocationScreen(
    navController: NavController,
    viewModel: PostViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val mapViewportState = rememberMapViewportState() {
        setCameraOptions() {
            pitch(0.0)
            zoom(14.0)

        }
    }
    val placeAutocomplete = PlaceAutocomplete.create()
    val density = LocalDensity.current.density
    val bitmap = BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.red_marker)
    val newWidth = (bitmap.width * 0.5).toInt() // 50% of original width
    val newHeight = (bitmap.height * 0.5).toInt() // 50% of original height
    val resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    val pinBitmap = resizedBitmap.asImageBitmap()
    var clickedPoint by remember { mutableStateOf<Point?>(null) }
    var place by remember {
        mutableStateOf<List<PlaceAutocompleteSuggestion>>(emptyList())
    }
    val searchEngine = SearchEngine.createSearchEngineWithBuiltInDataProviders(
        SearchEngineSettings()
    )
    var placeName by remember {
        mutableStateOf<String?>("")
    }
    var placeAddress by remember {
        mutableStateOf<String?>("")
    }
    var placeStreet by remember {
        mutableStateOf<String?>("")
    }
    var city by remember {
        mutableStateOf<String?>("")
    }

    val searchCallback = object : SearchCallback {
        override fun onResults(results: List<SearchResult>, responseInfo: ResponseInfo) {
            if (results.isEmpty()) {
                Log.i("SearchApiExample", "No reverse geocoding results")
            } else {
                Log.i("SearchApiExample", "Reverse geocoding results: $results")
                results.forEach { searchResult ->
                    placeAddress = searchResult.fullAddress
                    placeName = searchResult.name
                    placeStreet = searchResult.address?.street
                    city = searchResult.address?.place

                    Log.i("searchReusltNameM", searchResult.matchingName.toString())
                    Log.i("searchReuslt", city.toString())
                }
            }
        }

        override fun onError(e: Exception) {
            Log.i("SearchApiExample", "Reverse geocoding error", e)
        }
    }

    LaunchedEffect(clickedPoint) {
        Log.d("point", clickedPoint?.coordinates().toString())
        clickedPoint?.let {
            val reverseGeoCode = ReverseGeoOptions(
                center = Point.fromLngLat(it.longitude(), it.latitude()),
                limit = 1
            )
            val searchRequestTask = searchEngine.search(reverseGeoCode, searchCallback)
            Log.d("Reverse point", searchRequestTask.toString())
//            if (placeName == placeStreet) {
//                placeAddress?.let { address ->
//                    val response = placeAutocomplete.suggestions(query = address)
//                    response.onValue { list ->
//                        list.forEach {
//                            if (place.size <= 2) {
//                                Log.d("placeAutocomplete", it.toString())
//                                place = list
//                                placeName = place[0].name
//                                Log.d("PlaceAutocomplete", place[0].name.toString())
//
//                            }
//                            }
//
//
                        }
                    }



//        clickedPoint?.let {
//            val response = placeAutocomplete.suggestions(Point.fromLngLat(it.longitude(), it.latitude()))
//            Log.d("point", Point.fromLngLat(it.longitude(), it.latitude()).toString())
//            response.onValue {list ->
//                list.forEach {
//                    if (place.size <= 2){
//                        Log.d("placeAutocomplete", it.toString())
//                        place = list
//                    }
//                }
//
//            }
//        }


     


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp),
    ) {
        Box(modifier = Modifier.padding(it)) {
            MapboxMap(
                modifier = Modifier.fillMaxSize(),
                mapViewportState = mapViewportState,
                compass = {
                    Compass(
                        modifier = Modifier
                            .height(0.dp)
                            .width(0.dp)
                    )
                },
                scaleBar = {
                    ScaleBar(
                        height = 0.dp,
                        primaryColor = Color.Transparent,
                        secondaryColor = Color.Transparent,
                        textColor = Color.Transparent,
                        borderWidth = 0.dp,
                        textBorderWidth = 0.dp,
                        textSize = 0.sp
                    )
                }


            ) {
                if (clickedPoint != null) {

                    PointAnnotation(
                        point = clickedPoint!!,
                        iconImageBitmap = pinBitmap.asAndroidBitmap()
                    )
                }

                MapEffect(Unit) { mapView ->
                    mapView.location.updateSettings {
                        locationPuck = createDefault2DPuck(withBearing = true)
                        enabled = true
                        puckBearing = PuckBearing.COURSE
                        puckBearingEnabled = true
                    }
                    val locationComponentPlugin = mapView.location.apply {
                        // Enable the location component
                        this.enabled = true
                    }
                    mapView.mapboxMap.addOnMapClickListener { point ->
                        clickedPoint = point
                        true
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
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            if (clickedPoint != null) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 32.dp)
                        .height(150.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    (if (placeName != null) placeName else "")?.let { placeName ->
                        Text(
                            text = placeName,
                            Modifier.padding(8.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Log.d("PlaceName", placeName)
                    }
                    (if (placeAddress != null) placeAddress else "")?.let { placeAddress ->
                        Text(
                            text = placeAddress,
                            Modifier.padding(8.dp),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 12.sp
                        )
                    }
                    Box(Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd){
                        Button(modifier = Modifier.padding(bottom = 8.dp, end = 8.dp),
                            onClick = {
                            viewModel.pickPlace(
                            PickPlace(
                                name = placeName,
                                address = placeAddress,
                                city = city
                            ))
                            navController.navigate(Screen.NewPost.route)
                        }) {
                            Text(stringResource(R.string.confirm))
                        }
                    }

                }
            }
        }
    }
}

