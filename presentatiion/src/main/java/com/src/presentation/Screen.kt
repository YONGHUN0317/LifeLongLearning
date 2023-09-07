package com.src.presentation

sealed class Screen(val route: String){
    object SplashFirstView : Screen("splash_first_view")
    object SplashLocationView : Screen("splash_location_view")

}
