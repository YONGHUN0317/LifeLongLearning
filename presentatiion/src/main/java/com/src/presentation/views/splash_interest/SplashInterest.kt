package com.src.presentation.views.splash_interest

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.src.presentatiion.R
import com.src.presentation.ui.theme.LifeLongLearningTheme
import com.src.presentation.ui.theme.OrangeButton
import com.src.presentation.ui.theme.OrangeButtonPressed
import com.src.presentation.ui.theme.Selected
import com.src.presentation.ui.theme.UnSelected
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.coroutines.launch

@Composable
fun SplashInterestView(navController: NavController? = null, viewModel: SplashInterestViewModel) {
    val selectedInterests by viewModel.selectedInterests.observeAsState(setOf())
    val selectedInterestCount = selectedInterests.size

    val context = LocalContext.current
    val scope = rememberCoroutineScope()



    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "관심사 두개를 선택하세요 !",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.padding(top = 30.dp, bottom = 15.dp, start = 30.dp)
            )


            val interests = listOf("프로그래밍", "여행", "음악", "영화", "스포츠",
                "요리", "책", "영어", "역사", "투자", "심리", "운동",
                "사진", "원예", "패션")

            val rows = listOf(
                interests.subList(0, 3),
                interests.subList(3, 7),
                interests.subList(7, 11),
                interests.subList(11, 15)
            )

            rows.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 10.dp, bottom = 4.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    row.forEach { interest ->
                        InterestButton(
                            text = interest,
                            selected = selectedInterests.contains(interest),
                            onSelected = {
                                if (selectedInterestCount < 2) {
                                    viewModel.updateSelectedInterests(interest, true)
                                }
                            },
                            onDeselected = {
                                viewModel.updateSelectedInterests(interest, false)
                            },
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.splash_interest),
                contentDescription = "Main photo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp)
                    .size(268.dp, 330.dp),
                contentScale = ContentScale.Crop
            )
        }

        // Confirm Button
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        val bgColor = if (isPressed) OrangeButtonPressed else OrangeButton

        Button(
            onClick = {
                if (selectedInterestCount < 2) {
                    scope.launch {
                        StyleableToast.makeText(context, "관심사 두개를 골라주세요.", R.style.error).show()
                    }
                } else {
                    navController?.navigate("main")
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(281.dp, 55.dp)
                .offset(y = (-50).dp),
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = bgColor,
                contentColor = Color.White,
            )
        ) {
            Text(text = "선택", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }
}
@Composable
fun InterestButton(
    text: String,
    selected: Boolean,
    onSelected: () -> Unit,
    onDeselected: () -> Unit,
    modifier: Modifier = Modifier
) {
    val buttonColor = if (selected) Selected else UnSelected  // 변경된 부분

    Button(
        onClick = {
            if (selected) {
                onDeselected()
            } else {
                onSelected()
            }
        },
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,  // 변경된 부분
            contentColor = Color.Black
        ),
        modifier = modifier
    ) {
        Text(text = text)
    }

}





/*@Preview(showBackground = true)
@Composable
fun SplashLocationPreView() {
    LifeLongLearningTheme() {
        SplashInterestView()
    }
}*/
