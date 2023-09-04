package com.src.presentation.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.src.presentatiion.R
import com.src.presentation.ui.theme.LifeLongLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LifeLongLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Top section
        Column(
            modifier = Modifier
                .padding(start = 30.dp)
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "평생학습",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "행복하게 하는 교육",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "나의 삶을 풍요롭게",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_search),
                    contentDescription = "Search Image"
                )

                Image(
                    painter = painterResource(id = R.drawable.magnifyingglass),
                    contentDescription = "Magnifying Glass",
                    modifier = Modifier
                        .size(41.dp, 28.dp)
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)  // Add padding to the end side
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "추천 강좌",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text(
                    text = "더보기",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(end = 30.dp)
                )
            }
        }

        // Bottom section (Tabs & ViewPager)
        // Note: Implementing a fully functional TabLayout and ViewPager2 in Compose
        // is outside of the scope of this snippet. You would use something like `TabRow`
        // for the tabs and a combination of `LazyColumn` or `LazyRow` for the viewpager content.
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LifeLongLearningTheme {
        MainScreen()
    }
}