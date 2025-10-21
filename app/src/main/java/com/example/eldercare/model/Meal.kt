package com.example.eldercare.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.Exclude
import java.util.Calendar
import java.util.Date

enum class MealType {
    Breakfast,
    Lunch,
    Snack,
    Dinner,
}

data class Meal(
    @DocumentId val id: String = "",
    val patientUuid: String = "",
    val date: Timestamp? = null,
    val type: MealType = MealType.Breakfast,
    val dish: String = "",
    val gotEaten: Boolean = false,
    val additionalInfo: String = "",
) {
    @get:Exclude
    val missed: Boolean
        get() {
            if (gotEaten) return false
            if (date == null) return true

            val threshold = Calendar.getInstance().apply {
                time = date.toDate()
                add(Calendar.MINUTE, MINUTES_UNTIL_CONSIDERED_MISSED)
            }.time
            val now = Date()
            return now.after(threshold)
        }

    companion object {
        const val MINUTES_UNTIL_CONSIDERED_MISSED = 120
    }
}

val exampleBreakfastMeal = Meal(
    type = MealType.Breakfast,
    dish = "Two toast with cheese and an egg",
    date = null,
    additionalInfo = "No salt to the egg.",
    gotEaten = true,
)

val exampleLunchMeal = Meal(
    type = MealType.Lunch,
    dish = "Pasta with chicken and broccoli",
    date = null,
    additionalInfo = "",
    gotEaten = true,
)

val exampleSnackMeal = Meal(
    type = MealType.Snack,
    dish = "Banana and apple",
    date = null,
    additionalInfo = "Make sure that the banana is ripe.",
    gotEaten = true,
)

val exampleDinnerMeal = Meal(
    type = MealType.Dinner,
    dish = "Kötbullar with mashed potatoes",
    date = null,
    additionalInfo = "",
    gotEaten = false,
)