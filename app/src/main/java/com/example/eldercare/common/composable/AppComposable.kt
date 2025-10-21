package com.example.eldercare.common.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun Logo(logoSize: Int, painterResource: Painter) {
    Image(
        painter = painterResource,
        contentDescription = "",
        modifier = Modifier.size(logoSize.dp)

    )
}

/*@Composable
fun HeadLine(value: String, painterResource: Painter) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn()
            .background(color = Color.Blue),
    ) {
        Icon(painter = painterResource, contentDescription = "")
        Text(
            text = value,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            ),
            textAlign = TextAlign.Center
        )
    }
}*/
