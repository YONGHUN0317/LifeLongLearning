package com.src.presentation.views.splash_location

import android.content.pm.PackageManager
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.src.presentatiion.BuildConfig.google_map_key
import com.src.presentatiion.R
import com.src.presentation.ui.theme.LifeLongLearningTheme
import com.src.presentation.ui.theme.OrangeButton
import com.src.presentation.ui.theme.OrangeButtonPressed
import com.src.presentation.ui.theme.SemiBlue
import java.util.Locale


@Composable
fun SplashLocationView(navController: NavController? = null) {
    var query by remember { mutableStateOf("") }
    var suggestions by remember { mutableStateOf(listOf<AutocompletePrediction>()) }
    var selectedCoordinates by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    val context = LocalContext.current
    if (!Places.isInitialized()) {
        Places.initialize(context, google_map_key, Locale.getDefault())
    }
    val placesClient: PlacesClient = Places.createClient(context)
    val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(LocalContext.current)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 16f)
    }
    val userLocation = remember { mutableStateOf<LatLng?>(null) }


    fun fetchPlaceDetails(placeId: String) {
        val placeFields = listOf(Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.newInstance(placeId, placeFields)
        placesClient.fetchPlace(request)
            .addOnSuccessListener { response ->
                val place = response.place
                selectedCoordinates = Pair(place.latLng!!.latitude, place.latLng!!.longitude)
                cameraPositionState.position =
                    place.latLng?.let { CameraPosition.fromLatLngZoom(it, 16f) }!!
            }
            .addOnFailureListener { exception ->
                Log.e("PlaceDetails", "Error fetching place details: ${exception.message}")
            }
    }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return@LaunchedEffect
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
            val newLatLng = LatLng(location.latitude, location.longitude)
            cameraPositionState.position = CameraPosition.fromLatLngZoom(newLatLng, 16f)
            userLocation.value = newLatLng
        }
    }

    val bounds = RectangularBounds.newInstance(
        LatLng(37.0, 126.0),
        LatLng(38.0, 129.0)
    )

    LaunchedEffect(key1 = query) {
        if (query.isNotEmpty()) {
            val request = FindAutocompletePredictionsRequest.builder()
                .setQuery(query)
                .setLocationBias(bounds)
                .build()

            placesClient.findAutocompletePredictions(request)
                .addOnSuccessListener { response ->
                    suggestions = response.autocompletePredictions
                }
                .addOnFailureListener { exception ->
                    Log.e("AutoComplete", "Error fetching place details: ${exception.message}")
                }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = "현재 위치가 맞습니까?",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 30.dp, bottom = 15.dp, start = 30.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize() // 사이즈 변경
            ) {
                TextField(
                    value = query,
                    onValueChange = { newValue ->
                        query = newValue
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if (suggestions.isNotEmpty()) {
                                val firstSuggestionId = suggestions[0].placeId
                                fetchPlaceDetails(firstSuggestionId)
                            }
                        }
                    )
                )

                LazyColumn {
                    items(suggestions) { suggestion ->
                        Text(
                            text = suggestion.getFullText(null).toString(),
                            modifier = Modifier.clickable {
                                fetchPlaceDetails(suggestion.placeId)
                            }
                        )
                    }
                }
            }



            GoogleMap(
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false,
                    mapToolbarEnabled = false,
                    myLocationButtonEnabled = false
                ),
                properties = MapProperties(isMyLocationEnabled = true),
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
            ) {
                selectedCoordinates?.let { coordinates ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                coordinates.first,
                                coordinates.second
                            )
                        ),
                        title = "선택한 위치"
                    )
                }
            }

        }

        // Confirm Button
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val bgColor = if (isPressed) OrangeButtonPressed else OrangeButton
        Button(
            onClick = {
                navController?.navigate("splashInterest")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(281.dp, 55.dp)
                .offset(y = (-50).dp)
                .fillMaxWidth(),
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = bgColor,
                contentColor = Color.White,
            )
        ) {
            Text(text = "선택", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }

        FloatingActionButton(
            modifier = Modifier
                .padding(bottom = 160.dp, end = 16.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    val newLatLng = LatLng(location.latitude, location.longitude)
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(newLatLng, 16f)
                    userLocation.value = newLatLng
                }
            },
            contentColor = SemiBlue,
            shape = CircleShape,
            containerColor = Color.White
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Current Location",
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SplashLocationPreView() {
    LifeLongLearningTheme() {
        SplashLocationView()
    }
}
