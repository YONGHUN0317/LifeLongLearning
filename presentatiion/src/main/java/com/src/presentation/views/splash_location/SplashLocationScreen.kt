package com.src.presentation.views.splash_location

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.src.presentatiion.R
import com.src.presentation.ui.theme.LifeLongLearningTheme
import com.src.presentation.ui.theme.OrangeButton
import com.src.presentation.ui.theme.OrangeButtonPressed
import com.src.presentation.ui.theme.SemiBlue


@Composable
fun SplashLocationView(navController: NavController? = null) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        val context = LocalContext.current

        // Permission handling
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (!isGranted) {
                // Permission denied. Navigate to a different screen or finish the activity.
                navController?.navigate("PermissionDeniedRoute")
            }
        }


        val hasPermission = remember {
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            )
        }


        LaunchedEffect(Unit) {
            if (!hasPermission.value) {
                // Request permission
                permissionLauncher.launch(ACCESS_FINE_LOCATION)
            }
        }
        // Check permission before displaying map
        if (hasPermission.value) {
            // Google Map in the background
            val latLng = LatLng(37.7387295, 127.0458908)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(latLng, 15f)
            }
            // text
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

                GoogleMap(
                    uiSettings = MapUiSettings(
                        zoomControlsEnabled = false,
                        myLocationButtonEnabled = true,
                    ),
                    properties = MapProperties(isMyLocationEnabled = true),
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                ) {
                    Marker(
                        state = MarkerState(position = latLng),
                        title = "현재 위치"
                    )
                }

            }

            // Confirm Button
            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()
            val bgColor = if (isPressed) OrangeButtonPressed else OrangeButton
            Button(
                onClick = { /* navigate */ },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(281.dp, 55.dp)
                    .padding(bottom = 50.dp)
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
                    .background(Color.White)
                    .align(alignment = Alignment.BottomEnd),
                onClick = {},
                contentColor = SemiBlue,
                shape = CircleShape,
                containerColor = Color.White
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Current Location",
                )
            }

        } else {
            Text("Location permission is required to display the map.")
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
