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
    val showDialog = remember { mutableStateOf(true) }


    val updatePermissionState = {
        hasPermission.value = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        showDialog.value = !hasPermission.value
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            navController.navigate("splashFirst")
        } else {
            showDialog.value = true
        }
        updatePermissionState()
    }

    LaunchedEffect(Unit) {
        updatePermissionState()
        if (!hasPermission.value) {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            },
            onConfirmation = {
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            },
            dialogText = "이 앱을 계속 사용하려면 위치 권한이 필요합니다.\n권한을 부여하지 않으면 앱을 사용할 수 없습니다."
        )
    }
}

