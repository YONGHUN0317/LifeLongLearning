package com.src.presentation.views.allow

import android.Manifest
import android.content.pm.PackageManager
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
    val showDialog = remember { mutableStateOf(false) }

    val updatePermissionState = {
        hasPermission.value = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    // Initialize permission handling
    val permissionLauncher = rememberUpdatedState(
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (!isGranted) {
                showDialog.value = true
            } else {
                navController.navigate("splashFirst")
            }
            updatePermissionState()
        }
    )

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
                updatePermissionState()
            },
            onConfirmation = {
                permissionLauncher.value.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            },
            dialogText = "이 앱을 계속 사용하려면 위치 권한이 필요합니다."
        )
    }

    LaunchedEffect(Unit) {
        updatePermissionState()
        if (!hasPermission.value) {
            permissionLauncher.value.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            navController.navigate("splashFirst")
        }
    }
}

