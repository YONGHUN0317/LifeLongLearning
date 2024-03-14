package com.src.presentation.views.main

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.src.presentation.R

val Pretendard = FontFamily(
    Font(R.font.pretendard_light, FontWeight.Light),
    Font(R.font.pretendard_regular, FontWeight.Normal),
    Font(R.font.pretendard_bold, FontWeight.Bold)

)

@Composable
fun MainView(viewModel: MainViewModel = hiltViewModel(),
             navController: NavController) {
    val selectedInterests by viewModel.selectedInterests.collectAsState()
    val remainingInterests by viewModel.remainingInterests.collectAsState()
    val userLocation by viewModel.location.collectAsState()
    val context = LocalContext.current



    val titles = listOf(
        "학점은행제 · 독학학위제",
        "평생교육바우처",
        "국가문해센터",
        "K-MOOC",
        "평생교육사 자격관리",
        "평생학습계좌제",
        "늘배움",
        "학부모On누리",
        "검정고시지원센터"
    )
    val numbers = listOf(
        "1600-0400",
        "1600-3005",
        "1600-6759",
        "1811-3118",
        "1577-3867",
        "02-3780-9986",
        "02-3780-9958",
        "02-3780-9936",
        "1800-4602"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
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
                    text = userLocation,
                    modifier = Modifier.padding(start = 8.dp, top = 3.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold) // 볼드체로 변경
                )
            }
            Image(
                painter = painterResource(id = R.drawable.search_bar),
                contentDescription = "main_search_bar",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, top = 20.dp)
                    .clickable(
                        indication = null, // 시각적 효과 제거
                        interactionSource = remember { MutableInteractionSource() } // 기본 상호작용 추적 안함
                    ) {
                        navController.navigate("search")
                    }
            )
            Text(
                text = "관심사 강의",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                style = TextStyle(fontSize = 24.sp)
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(top = 10.dp, start = 10.dp)
            ) {
                selectedInterests.forEach { interest ->
                    Card(
                        name = interest,
                        imageRes = getImageResource(interest),
                        backgroundColor = colorResource(id = getColorResource(interest))
                    )
                }
            }

            Text(
                text = "추천 강의",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                style = TextStyle(fontSize = 24.sp)
            )
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(top = 10.dp, start = 10.dp)
            ) {
                remainingInterests.forEach { interest ->
                    Card(
                        name = interest,
                        imageRes = getImageResource(interest),
                        backgroundColor = colorResource(id = getColorResource(interest))
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.main_bottom_color))
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(titles) { index, title ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .height(40.dp)
                            .wrapContentHeight()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .noRippleClickable {
                                        val phoneNumber = numbers[index]
                                        val intent = Intent(Intent.ACTION_DIAL).apply {
                                            data = Uri.parse("tel:$phoneNumber")
                                        }
                                        startActivity(context, intent, null)
                                    }
                                    .padding(horizontal = 4.dp)
                            ) {
                                Text(
                                    text = title,
                                    fontFamily = Pretendard,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = numbers[index],
                                    fontFamily = Pretendard,
                                    fontWeight = FontWeight.Light,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }
                            if (index < titles.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .height(24.dp)
                                        .width(1.dp)
                                        .padding(vertical = 4.dp),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

fun getImageResource(interest: String) = when (interest) {
    "요리" -> R.drawable.cooking
    "영어" -> R.drawable.english
    "음악" -> R.drawable.music
    "프로그래밍" -> R.drawable.programming
    "여행" -> R.drawable.travel
    "영화" -> R.drawable.movie
    "스포츠" -> R.drawable.sport
    "책" -> R.drawable.reading
    "역사" -> R.drawable.history
    "투자" -> R.drawable.earning
    "심리" -> R.drawable.psychology
    "운동" -> R.drawable.exercise
    "사진" -> R.drawable.photographer
    "원예" -> R.drawable.horticulture
    "패션" -> R.drawable.fashion

    else -> R.drawable.question_mark
}

fun getColorResource(interest: String) = when (interest) {
    "요리" -> R.color.cooking
    "영어" -> R.color.english
    "음악" -> R.color.music
    "프로그래밍" -> R.color.programming
    "여행" -> R.color.travel
    "영화" -> R.color.movie
    "스포츠" -> R.color.sport
    "책" -> R.color.reading
    "역사" -> R.color.history
    "투자" -> R.color.earning
    "심리" -> R.color.psychology
    "운동" -> R.color.exercise
    "사진" -> R.color.photographer
    "원예" -> R.color.horticulture
    "패션" -> R.color.fashion

    else -> R.color.white
}

@Composable
fun Card(name: String, @DrawableRes imageRes: Int, backgroundColor: Color) {
    val imagePainter = // 이미지 로딩 시 크로스페이드 효과 사용
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = imageRes).apply(block = fun ImageRequest.Builder.() {
                memoryCachePolicy(CachePolicy.ENABLED)
                diskCachePolicy(CachePolicy.ENABLED)
                crossfade(true) // 이미지 로딩 시 크로스페이드 효과 사용
            }).build()
        )
    Box(
        modifier = Modifier
            .padding(4.dp)
            .width(120.dp)
            .height(140.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Image(
                painter = imagePainter,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = backgroundColor)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = name, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun SplashLocationPreView() {
    LifeLongLearningTheme {
        MainView()
    }
}*/
