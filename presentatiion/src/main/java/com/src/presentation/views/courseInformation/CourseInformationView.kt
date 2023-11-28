package com.src.presentation.views.courseInformation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.src.presentation.ui.theme.LifeLongLearningTheme

@Composable
fun CourseInformationView() {
    Information()
}

@Composable
fun Information() {
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // title
        Text(
            text = "안내센터 방문예약 확인서",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
        )
        TitleRow("강좌 정원수", "수강료", "선정방법구분")
        TitleRow("접수방법구분", "교육방법구분", "교육대상구분")
        TitleRow("교육장소", "운영기관 전화번호")
        SingleTitleRow("접수날짜")
        SingleTitleRow("교육날짜")
        SingleTitleRow("홈페이지 주소")
        SingleTitleRow("강좌 내용")
        SingleTitleRow("강사명")
        SingleTitleRow("직업능력 개발 훈련비 지원강좌여부")
        SingleTitleRow("학점은행제평가(학점)인정여부")
        SingleTitleRow("평생학습계좌 평가 인정여부")
        SingleTitleRow("데이터 기준 날짜")
    }
}

@Composable
fun TitleRow(vararg texts: String) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        texts.forEach { text ->
            Text(
                text = text,
                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp)
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun SingleTitleRow(text: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}


@Composable
fun InfoRow(vararg texts: String) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        texts.forEach { text ->
            Text(
                text = text,
                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp)
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun SingleInfoRow(text: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 18.sp)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}



@Preview(showBackground = true)
@Composable
fun SplashLocationPreView() {
    LifeLongLearningTheme {
        CourseInformationView()
    }
}