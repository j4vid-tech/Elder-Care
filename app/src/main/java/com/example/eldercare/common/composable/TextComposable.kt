package com.example.eldercare.common.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Greeting(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth(),
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center

    )
}

@Composable
fun Headline(value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(50.dp)
            .background(
                color = MaterialTheme.colorScheme.primary
            )
    ) {
        Text(
            text = value,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 28.sp,
                //fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            ), textAlign = TextAlign.Center
        )
    }
}
