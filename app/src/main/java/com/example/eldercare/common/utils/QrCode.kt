package com.example.eldercare.common.utils

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

fun generateQrCode(textToEncode: String, size: Int = 512): Bitmap {
    val textToEncode = if (textToEncode == "") "noValue" else textToEncode // !THIS IS A BAD WORKAROUND TO HANDLE EMPTY STRINGS!
    val hints = hashMapOf<EncodeHintType, Int>().also {
        it[EncodeHintType.MARGIN] = 1
    }
    val bits = QRCodeWriter().encode(
        textToEncode,
        BarcodeFormat.QR_CODE,
        size,
        size,
        hints
    )
    return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
        for (x in 0 until size) {
            for (y in 0 until size) {
                it.setPixel(
                    x,
                    y,
                    if (bits[x, y]) Color.BLACK else Color.WHITE
                )
            }
        }
    }
}