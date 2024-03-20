package com.src.presentation.views.lectureInfo

import android.content.ActivityNotFoundException
import android.content.Intent
import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.location.component1
import androidx.core.location.component2
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.src.domain.model.LectureEntity
import com.src.presentation.R
import java.net.URLDecoder


@Composable
fun DetailPage(viewModel: LectureInfoViewModel = hiltViewModel(),  lectureJson: String, navController: NavController) {
    val lecture: LectureEntity = Gson().fromJson(URLDecoder.decode(lectureJson, "utf-8"), LectureEntity::class.java)
    val location by viewModel.locationState.collectAsState()
    viewModel.updateLocationForAddress(lecture.edcRdnmadr)

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                location?.let { GoogleMapsSection(it, lecture.edcPlace) }
                DetailTopAppBarOverlay(navController)
            }
            InformationCardSection(lecture)
        }
    }


}

@Composable
fun GoogleMapsSection(location : Location, edcPlace : String) {
    Log.d("DetailPage", "${location.component1()} ${location.component2()} $edcPlace")
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(location.component1(), location.component2()),
            15f
        )
    }

    GoogleMap(
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            mapToolbarEnabled = false,
            myLocationButtonEnabled = false
        ),
        properties = MapProperties(isMyLocationEnabled = true),

        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .zIndex(1f),
        cameraPositionState = cameraPositionState,
    ) {
        Marker(
            state = MarkerState(
                position = LatLng(
                    location.component1(),
                    location.component2()
                )
            ),
            title = edcPlace
        )
    }
}

@Composable
fun InformationCardSection(lecture: LectureEntity) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = lecture.lectureName,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "강좌 정원 수",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "수강료",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "선정 방법 구분",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = lecture.psncpa,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = lecture.lctreCost,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = lecture.slctnMthType,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "접수방법구분",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "교육대상구분",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = lecture.rceptMthType,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = lecture.edcTrgetType,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "교육장소",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "운영기관 전화번호",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = lecture.edcPlace,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = lecture.operPhoneNumber,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.Blue,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${lecture.operPhoneNumber}")
                            }
                            context.startActivity(intent)
                        }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "접수날짜",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${lecture.rceptStartDate} ~ ${lecture.rceptEndDate}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "교육날짜",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "${lecture.edcStartDay } ~ ${lecture.edcEndDay}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "교육일자",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "시간",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = lecture.operDay,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${lecture.edcStartTime} ~ ${lecture.edcColseTime}",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "홈페이지 주소",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )

            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = lecture.homepageUrl,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                        .clickable {
                            if (lecture.homepageUrl.startsWith("http://") || lecture.homepageUrl.startsWith("https://")) {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(lecture.homepageUrl))
                                context.startActivity(intent)
                            } else {
                                val correctedUrl = "http://${lecture.homepageUrl}"
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(correctedUrl))
                                try {
                                    context.startActivity(intent)
                                } catch (e: ActivityNotFoundException) {
                                    Log.e("DetailPage", "Error Url", e)
                                    Toast.makeText(context,"URL 인텐트를 처리할 활동이 없습니다..", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                    fontWeight = FontWeight.Bold, color = Color.Blue
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            CommentSection(
                teacherName = lecture.instructorName,
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "강좌 내용",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = lecture.lctreCo,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "직업능력개발훈련비 지원강좌여부",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (lecture.oadtCtLctreYn == "N") "지원안됩니다" else "지원됩니다",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "학점은행제평가(학점)인정여부",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (lecture.pntBankAckestYn == "N") "지원안됩니다" else "지원됩니다",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "평생학습계좌제평가인정여부",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (lecture.lrnAcnutAckestYn == "N") "지원안됩니다" else "지원됩니다",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "데이터 기준 날짜 ",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = lecture.referenceDate,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBarOverlay(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .zIndex(10f) // 지도 위에 있는지 확인
    ) {
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로가기")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent, // 앱바 투명
                navigationIconContentColor = Color.Black,
                titleContentColor = Color.Transparent
            )
        )
    }
}

@Composable
fun CommentSection(teacherName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                teacherName,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
