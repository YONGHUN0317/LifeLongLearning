package com.src.presentation.views.search

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.gson.Gson
import com.src.domain.model.LectureEntity
import com.src.presentation.ui.theme.LifeLongLearningTheme
import java.net.URLEncoder

/**
 * 검색 화면
 * @param viewModel
 * @param navController
 */
@Composable
fun SearchView(viewModel: SearchViewModel = hiltViewModel(), navController: NavController? = null) {
    val searchQuery = remember { mutableStateOf("") }
    /**
     * 검색 
     */
    Column {
        CustomSearchBar(
            searchQuery = searchQuery.value,
            onSearchQueryChanged = { query ->
                searchQuery.value = query
                viewModel.searchLectures(query)
            },
            onClearQuery = {
                searchQuery.value = ""
                viewModel.searchLectures("")
            }
        )
        val lectures: LazyPagingItems<LectureEntity> = viewModel.lectures.collectAsLazyPagingItems()

        LaunchedEffect(lectures.itemCount) {
            Log.d("SearchView", "현재 아이템: ${lectures.itemCount}")
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(lectures.itemCount) { index ->
                lectures[index]?.let { lecture ->
                    Item(
                        title = lecture.lctreNm,
                        location = lecture.edcRdnmadr,
                        date = lecture.edcStartDay
                    ) {
                        val lectureJson = Gson().toJson(lecture)
                        navController?.navigate("lectureInfo/${URLEncoder.encode(lectureJson, "utf-8")}")
                    }

                    Log.d("SearchView", "인덱스 $index: ${lecture.lctreNm}")
                }
            }
        }


    }
}

/**
 * 정보 Item
 * @param title
 * @param location
 * @param date
 * @param onClick
 * @receiver
 */
@Composable
fun Item(title: String, location: String, date: String, onClick: () -> Unit) {
    Box(

        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(bottom = 10.dp)
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
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(1.dp)
                    .padding(horizontal = 15.dp),
                color = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onClearQuery: () -> Unit
) {

    val textStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
    TextField(
        value = searchQuery,
        onValueChange = onSearchQueryChanged,
        singleLine = true,
        textStyle = textStyle,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text("Search...") },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = onClearQuery) {
                    Icon(Icons.Default.Close, contentDescription = "Clear Icon")
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.Black,
            containerColor = Color(0xFFE6E6E6),
            focusedIndicatorColor = Color(0xFFFFA500),
            unfocusedIndicatorColor = Color.LightGray
        ),
        shape = RoundedCornerShape(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreView() {
    LifeLongLearningTheme {
        SearchView()
    }
}
