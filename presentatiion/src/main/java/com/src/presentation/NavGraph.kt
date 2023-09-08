package com.src.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.src.presentation.views.splash_first.SplashFirstView
import com.src.presentation.views.splash_location.SplashLocationView

@Composable
fun MainContent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splashFirst") {
        composable("splashFirst") {
            SplashFirstView(navController)
        }
        composable("splashLocation") {
            SplashLocationView(navController)
        }
        composable("splashInterest") {
            SplashLocationView(navController)
        }
    }
}
