package com.example.eldercare.common.ext

import com.example.eldercare.model.Meal
import java.time.Duration
import java.time.Instant

fun Meal.missedInLast15Minutes(): Boolean {
    val datetime = this.date!!.toDate().toInstant().plusSeconds((Meal.MINUTES_UNTIL_CONSIDERED_MISSED * 60).toLong())
    val currentTime = Instant.now()
    val fifteenMinAgo = currentTime.minus(Duration.ofMinutes(15))

    return datetime.isAfter(fifteenMinAgo) && datetime.isBefore(currentTime)
}