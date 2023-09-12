package com.src.presentation.views.splash_first

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.src.presentatiion.R
import com.src.presentation.ui.theme.OrangeButton
import com.src.presentation.ui.theme.OrangeButtonPressed

@Composable
fun SplashFirstView(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(start = 30.dp)
        ) {
            Text(
                text = "평생학습",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 30.dp)
            )
            Text(
                text = "행복하게 하는 교육",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 15.dp)
            )
            Text(
                text = "나의 삶을 풍요롭게",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 15.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.splash_first),
            contentDescription = "Main photo",
            modifier = Modifier
                .align(Alignment.Center)
                .size(268.dp, 372.dp),
            contentScale = ContentScale.Crop  // 이 부분을 추가했습니다.
        )
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val bgColor = if (isPressed) OrangeButtonPressed else OrangeButton
        Button(

            onClick = {navController.navigate("splashLocation") },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
                .size(281.dp, 55.dp),
            interactionSource = interactionSource, // 이 부분을 추가합니다.
            colors = ButtonDefaults.buttonColors(
                containerColor = bgColor,
                contentColor = White
            )
        ) {
            Text(text = "환영해요", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun SplashPreView() {
    LifeLongLearningTheme() {
        SplashFirstView()
    }
}*/
