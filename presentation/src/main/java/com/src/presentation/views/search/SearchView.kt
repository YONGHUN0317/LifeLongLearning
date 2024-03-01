package com.src.presentation.views.search

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.src.domain.model.LectureEntity
import com.src.presentation.ui.theme.LifeLongLearningTheme


@Composable
fun SearchView(viewModel: SearchViewModel = hiltViewModel(), navController: NavController? = null){
    val lectures: LazyPagingItems<LectureEntity> = viewModel.lectures.collectAsLazyPagingItems()

    LaunchedEffect(lectures.itemCount) {
        Log.d("SearchView", "Current item count: ${lectures.itemCount}")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(lectures.itemCount) { index ->
            lectures[index]?.let { lecture ->
                Item(title = lecture.lctreNm, location = lecture.edcRdnmadr, date = lecture.edcStartDay)

                Log.d("SearchView", "Loading item at index $index: ${lecture.lctreNm}")
            }
        }
    }
}



@Composable
fun Item(title: String, location : String, date: String){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Text(
                text = location,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 18.sp,
                ),
            )
            Text(
                text = date,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 18.sp,
                ),
            )
            Divider(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(horizontal = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreView() {
    LifeLongLearningTheme {
        SearchView()
    }
}
