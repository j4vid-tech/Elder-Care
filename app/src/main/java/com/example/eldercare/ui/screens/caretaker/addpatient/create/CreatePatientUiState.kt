package com.example.eldercare.ui.screens.caretaker.addpatient.create

data class CreatePatientUiState(
    val fullName: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: String = "",
    val foodInterests: String = "",
    val dietaryRestrictions: String = "",
    val password: String = "",
    val repeatPassword: String = "",
)
