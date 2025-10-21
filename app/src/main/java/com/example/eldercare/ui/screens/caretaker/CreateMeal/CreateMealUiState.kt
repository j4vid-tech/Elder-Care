package com.example.eldercare.ui.screens.caretaker.CreateMeal

import com.example.eldercare.model.MealType
import com.google.firebase.Timestamp



data class CreateMealUiState(
    val patientUuid: String = "",
    val date: Timestamp?,
    val dateString: String = "",
    val timeString: String = "",
    val type: MealType,
    val mealType: String = "",
    val dish: String = "",
    val gotEaten: Boolean = false,
    val additionalInfo: String = "",

)