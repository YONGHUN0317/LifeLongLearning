package com.src.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.src.data.datasource.local.PreferencesManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesManager: PreferencesManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isFirstTime = remember { mutableStateOf(true) }
            LaunchedEffect(key1 = "isFirstLaunch") {
                preferencesManager.isFirstLaunch.collect { isFirst ->
                    isFirstTime.value = isFirst
                }
            }
            if (isFirstTime.value) {
                OnboardScreen() // 온보딩 화면으로 이동
            } else {
                Main() // 메인 화면으로 이동
            }
        }
    }
}


