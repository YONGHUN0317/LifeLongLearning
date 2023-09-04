package com.src.presentatiion.views.splash_location

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentatiion.ui.theme.LifeLongLearningTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashLocationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashLocationView()
        }
    }
}

@Composable
fun SplashLocationView() {
    // Here you can define the UI for the splash location screen.
    // You can take inspiration from SplashFirstView
}

@Preview(showBackground = true)
@Composable
fun SplashLocationPreView() {
    LifeLongLearningTheme() {
        SplashLocationView()
    }
}
