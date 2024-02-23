package com.src.presentation.views.component

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.src.presentation.R


@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogText: String,
) {
    androidx.compose.material3.AlertDialog(
        icon = {
            Icon(painter = painterResource(R.drawable.info), contentDescription = "Icon")
        },

        text = {
            Text(text = dialogText,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold  // Optional, if you also want to set the font weight
                )
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("권한 허용")
            }
        },
    )
}