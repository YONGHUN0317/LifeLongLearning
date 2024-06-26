package com.src.presentation.views.onBoarding_location

import android.content.pm.PackageManager
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.src.presentation.R
import com.src.presentation.ui.theme.OrangeButton
import com.src.presentation.ui.theme.OrangeButtonPressed
import com.src.presentation.ui.theme.SemiBlue
import java.util.Locale

/**
 * 사용자가 현재 위치를 확인하거나 선택할 수 있도록 온보딩 위치 보기를 표시하는 구성 가능한 기능
 *
 * @param navController
 * @param viewModel
 */
@Composable
fun OnBoardingLocationView(
    navController: NavController? = null,
    viewModel: OnBoardingLocationViewModel = hiltViewModel(),
) {
    var userLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    var query by remember { mutableStateOf("") }
    var suggestions by remember { mutableStateOf(listOf<AutocompletePrediction>()) }
    val defaultYeouidoLocation = LatLng(37.532600, 126.911300)
    val context = LocalContext.current

    initializePlaces(context)
    val placesClient: PlacesClient = Places.createClient(context)
    val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(LocalContext.current)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 16f)
    }
    var showDialog by remember { mutableStateOf(false) }


    fun fetchPlaceDetails(placeId: String) {
        val placeFields = listOf(Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.newInstance(placeId, placeFields)
        placesClient.fetchPlace(request)
            .addOnSuccessListener { response ->
                val place = response.place
                userLocation = Pair(place.latLng!!.latitude, place.latLng!!.longitude)
                cameraPositionState.position =
                    place.latLng?.let { CameraPosition.fromLatLngZoom(it, 16f) }!!
            }
            .addOnFailureListener { exception ->
                Log.e("SplashLocationView", "장소 세부 정보 가져오기 오류: ${exception.message}")
            }
    }


    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            cameraPositionState.position =
                CameraPosition.fromLatLngZoom(defaultYeouidoLocation, 16f)
            userLocation = Pair(defaultYeouidoLocation.latitude, defaultYeouidoLocation.longitude)
        } else {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                val latLng = location?.let {
                    LatLng(it.latitude, it.longitude)
                } ?: defaultYeouidoLocation
                cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, 16f)
                userLocation = Pair(latLng.latitude, latLng.longitude)
            }
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
                    Log.e("SplashLocationView", "장소 세부 정보를 가져오는 중 오류가 발생했습니다: ${exception.message}")
                }
        }
    }
    /**
     *  Google 지도 보기, 검색창, 위치 확인 또는 새로고침 버튼을 포함한 기본 UI 구조
     */
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "현재 위치가 맞습니까?",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 30.dp, bottom = 15.dp, start = 30.dp)
            )

            GoogleMap(
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false,
                    mapToolbarEnabled = false,
                    myLocationButtonEnabled = false
                ),
                properties = MapProperties(isMyLocationEnabled = true),
                modifier = Modifier
                    .zIndex(1f),
                cameraPositionState = cameraPositionState,
            ) {
                userLocation?.let { coordinates ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                coordinates.first,
                                coordinates.second
                            )
                        ),
                        title = "위치"
                    )
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.search_bar),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 100.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        showDialog = true
                    }
            )
        }

        // Confirm Button
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val bgColor = if (isPressed) OrangeButtonPressed else OrangeButton
        Button(
            onClick = {
                userLocation?.let { coordinates ->
                    viewModel.updateLocation(coordinates)
                    navController?.navigate("splashInterest")
                }
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
                    location?.let {
                        val newLatLng = LatLng(it.latitude, it.longitude)
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(newLatLng, 16f)
                        userLocation = Pair(it.latitude, it.longitude)
                    }
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
    /**
     *  주소 찾기
     */
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(modifier = Modifier.height(200.dp)) {
                Column {
                    TextField(
                        modifier = Modifier
                            .zIndex(2f)
                            .fillMaxWidth(),
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
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .zIndex(2f)
                    ) {
                        items(suggestions) { suggestion ->
                            Text(
                                text = suggestion.getFullText(null).toString(),
                                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 15.sp),
                                modifier = Modifier.clickable {
                                    fetchPlaceDetails(suggestion.placeId)
                                    showDialog = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Get google maps api key
 *
 * @param context
 * @return
 */
fun getGoogleMapsApiKey(context: Context): String {
    return try {
        val applicationInfo = context.packageManager.getApplicationInfo(
            context.packageName,
            PackageManager.GET_META_DATA
        )
        applicationInfo.metaData?.getString("com.google.android.geo.API_KEY")
            ?: throw IllegalStateException("API key not found")
    } catch (e: Exception) {
       ""
    }
}


fun initializePlaces(context: Context) {
    val apiKey = getGoogleMapsApiKey(context)
    if (!Places.isInitialized() && apiKey.isNotEmpty()) {
        Places.initialize(context, apiKey, Locale.getDefault())
    }
}

/*@Preview(showBackground = true)
@Composable
fun SplashLocationPreView() {
    LifeLongLearningTheme {
        SplashLocationView()
    }
}*/
