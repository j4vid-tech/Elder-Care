package com.example.eldercare.model.service.impl

import com.example.eldercare.model.Caretaker
import com.example.eldercare.model.repository.impl.CaretakerRepository
import com.example.eldercare.model.service.AuthService
import com.example.eldercare.model.service.CaretakerAccountService
import javax.inject.Inject

class CaretakerAccountServiceImpl @Inject constructor(
    override val authService: AuthService,
    override val caretakerRepository: CaretakerRepository,
) : CaretakerAccountService {

    override suspend fun signUp(
        fullName: String,
        email: String,
        password: String
    ) {
        authService.createUserWithEmailAndPassword(email, password)
        caretakerRepository.add(
            Caretaker(
                ownerId = authService.currentUserId,
                fullName = fullName,
                isFormal = null,
            )
        )
    }

    override suspend fun addPatient(patientId: String) {
        val caretaker = caretakerRepository.getByOwnerId(authService.currentUserId)
        caretakerRepository.addPatient(patientId, caretaker.id)
    }
}
