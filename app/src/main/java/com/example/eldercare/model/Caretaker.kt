package com.example.eldercare.model

import com.google.firebase.firestore.DocumentId

data class  Caretaker(
    @DocumentId val id: String = "",
    val ownerId: String = "",
    val fullName: String = "",
    val isFormal: Boolean? = null,
    val patients: List<String> = emptyList(),
)
