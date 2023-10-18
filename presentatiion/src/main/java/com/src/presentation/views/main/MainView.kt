package com.src.presentation.views.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.src.presentatiion.R
import com.src.presentation.ui.theme.LifeLongLearningTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainView() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Column {
            Text(
                text = "평생 학습\n" +
                        "나의 삶을 풍요롭고\n" +
                        "행복하게 하는 교육",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 45.sp
                ),
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp)
            )
            Row(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.main_location),
                    contentDescription = "main_location",
                    modifier = Modifier
                        .size(24.dp, 24.dp)
                )
                Text(
                    text = "서울특별시 구로구 구일로4길 65",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.search_bar),
                contentDescription = "main_search_bar",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 20.dp)
            )
            Text(
                text = "관심사 강의",
                modifier = Modifier.padding(top = 10.dp),
                style = TextStyle(fontSize = 24.sp)

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f) // Row의 가로 길이를 1/3 가져간다
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.cooking),
                        contentDescription = "interesting1",
                        modifier = Modifier
                            .size(100.dp, 100.dp)
                    )
                    Text(
                        text = "관심사1",
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // 관심사2에 대한 Image와 설명
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f) // Row의 가로 길이를 1/3 가져간다
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.english),
                        contentDescription = "interesting2",
                        modifier = Modifier
                            .size(100.dp, 100.dp)

                    )
                    Text(
                        text = "관심사2",
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                // 관심사3에 대한 Image와 설명
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f) // Row의 가로 길이를 1/3 가져간다
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.exercise),
                        contentDescription = "interesting3",
                        modifier = Modifier
                            .size(100.dp, 100.dp)

                    )
                    Text(
                        text = "관심사3",
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
            Text(
                text = "추천 강의",
                modifier = Modifier.padding(top = 10.dp),
                style = TextStyle(fontSize = 24.sp)
            )

            val pageCount = 4
            val pagerState = rememberPagerState(pageCount = {
                pageCount
            })
            HorizontalPager(
                state = pagerState
            ) { page ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f) // Row의 가로 길이를 1/3 가져간다
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.cooking),
                            contentDescription = "interesting1",
                            modifier = Modifier
                                .size(100.dp, 100.dp)
                        )
                        Text(
                            text = "관심사1",
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f) // Row의 가로 길이를 1/3 가져간다
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.english),
                            contentDescription = "interesting2",
                            modifier = Modifier
                                .size(100.dp, 100.dp)

                        )
                        Text(
                            text = "관심사2",
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f) // Row의 가로 길이를 1/3 가져간다
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.exercise),
                            contentDescription = "interesting3",
                            modifier = Modifier
                                .size(100.dp, 100.dp)

                        )
                        Text(
                            text = "관심사3",
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(top = 4.dp , end = 2.dp, start = 2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(17.dp)

                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashLocationPreView() {
    LifeLongLearningTheme() {
        MainView()
    }
}