package com.src.presentation.views.allow

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.src.presentation.views.component.AlertDialog

@Composable
fun AllowScreen(navController: NavController) {
    val context = LocalContext.current
    val hasPermission = remember { mutableStateOf(false) }

    val updatePermissionState = {
        hasPermission.value = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }


    val permissionLauncher = rememberUpdatedState(
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) {
            navController.navigate("splashFirst")
            updatePermissionState()
        }
    )

    LaunchedEffect(Unit) {
        updatePermissionState()
        if (!hasPermission.value) {
            permissionLauncher.value.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            navController.navigate("splashFirst")
        }
    }
}

