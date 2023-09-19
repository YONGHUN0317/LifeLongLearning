package com.src.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.src.presentation.views.allow.AllowScreen
import com.src.presentation.views.splash_first.SplashFirstView
import com.src.presentation.views.splash_interest.SplashInterestView
import com.src.presentation.views.splash_location.SplashLocationView

@Composable
fun MainContent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "allow") {
        composable("allow") {
            AllowScreen(navController)
        }
        composable("splashFirst") {
            SplashFirstView(navController)
        }
        composable("splashLocation") {
            SplashLocationView(navController)
        }
        composable("splashInterest") {
            SplashInterestView(navController)
        }
    }



}
