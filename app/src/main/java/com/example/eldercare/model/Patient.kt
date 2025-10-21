package com.example.eldercare.model

import com.google.firebase.firestore.DocumentId

data class Patient(
    @DocumentId val id: String = "",
    val fullName: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: String = "",
    val foodInterests: String = "",
    val dietaryRestrictions: String = "",
)
