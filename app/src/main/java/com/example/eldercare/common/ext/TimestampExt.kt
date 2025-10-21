package com.example.eldercare.common.ext

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

fun Timestamp.getHoursAndMinutes(): String {
    val date = this.toDate()
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return formatter.format(date)
}

fun Timestamp.getDateAndTime(): String {
    val date = this.toDate()
    val formatter = SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault())
    return formatter.format(date)
}