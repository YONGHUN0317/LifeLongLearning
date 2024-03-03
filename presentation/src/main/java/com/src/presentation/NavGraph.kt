package com.src.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.src.presentation.views.allow.AllowScreen
import com.src.presentation.views.main.MainView
import com.src.presentation.views.onBoarding_first.OnBoardingFirstView
import com.src.presentation.views.onBoarding_interest.OnBoardingInterestView
import com.src.presentation.views.onBoarding_location.OnBoardingLocationView
import com.src.presentation.views.search.SearchView

@Composable
fun OnboardScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "allow") {
        composable("allow") {
            AllowScreen(navController)
        }
        composable("splashFirst") {
            OnBoardingFirstView(navController)
        }
        composable("splashLocation") {
            OnBoardingLocationView(navController)
        }
        composable("splashInterest") {
            OnBoardingInterestView(navController)
        }
        composable("main") {
            MainView(navController = navController)
        }
    }
}


@Composable
fun Main() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainView(navController = navController)

        }

        composable("search") {
            SearchView(navController = navController)
        }

        composable("splashLocation") {
            OnBoardingLocationView(navController)
        }
    }
}
