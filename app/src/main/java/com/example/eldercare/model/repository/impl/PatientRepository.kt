package com.example.eldercare.model.repository.impl

import com.example.eldercare.model.Patient
import com.example.eldercare.model.service.AuthService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

const val PATIENT_COLLECTION_NAME = "patient"


class PatientRepository @Inject constructor(
    firestore: FirebaseFirestore, auth: AuthService
) : FirestoreRepository<Patient>(collectionName = PATIENT_COLLECTION_NAME, firestore, auth) {

    override val clazz: Class<Patient>
        get() = Patient::class.java

    override fun getAll(): Flow<List<Patient>> {
        return collection.dataObjects()
    }

    override suspend fun add(data: Patient) {
        collection.document(data.id).set(data).await()
    }
}
