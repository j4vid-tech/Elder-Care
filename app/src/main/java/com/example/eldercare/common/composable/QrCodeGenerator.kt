package com.example.eldercare.common.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import com.example.eldercare.common.utils.generateQrCode

@Composable
fun QrCodeGenerator(textToEncode: String, contentDescription: String? = "Qr Code") {
    Image(
        bitmap = generateQrCode(textToEncode).asImageBitmap(),
        contentDescription = contentDescription,
    )
}
