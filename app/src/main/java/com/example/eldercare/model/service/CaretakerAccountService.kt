package com.example.eldercare.model.service

import com.example.eldercare.model.Caretaker
import com.example.eldercare.model.repository.impl.CaretakerRepository
import kotlinx.coroutines.flow.Flow

interface CaretakerAccountService {
    val authService: AuthService
    val caretakerRepository: CaretakerRepository
    suspend fun signUp(fullName: String, email: String, password: String)
    suspend fun addPatient(patientId: String)
    //fun getCaretaker(): Flow<Caretaker>
}