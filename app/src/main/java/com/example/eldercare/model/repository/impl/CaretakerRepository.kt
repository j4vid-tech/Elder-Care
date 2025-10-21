package com.example.eldercare.model.repository.impl

import com.example.eldercare.model.Caretaker
import com.example.eldercare.model.service.AuthService
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

const val CARETAKER_COLLECTION_NAME = "caretaker"


class CaretakerRepository @Inject constructor(
    firestore: FirebaseFirestore,
    auth: AuthService,
) : FirestoreRepository<Caretaker>(
    collectionName = CARETAKER_COLLECTION_NAME,
    firestore,
    auth,
) {
    override val clazz: Class<Caretaker>
        get() = Caretaker::class.java

    override fun getAll(): Flow<List<Caretaker>> {
        return collection.dataObjects()
    }

    suspend fun getByOwnerId(ownerId: String): Caretaker {
        return collection.whereEqualTo(Caretaker::ownerId.name, ownerId).dataObjects<Caretaker>().first()[0]
    }

    fun addPatient(patientId: String, caretakerId: String) {
        collection.document(caretakerId).update("patients", FieldValue.arrayUnion(patientId))
    }

    //TEST
    /*fun getPatientsForCaretaker(caretakerId: String, onPatientsFetched: (List<String>) -> Unit) {
        collection.document(caretakerId).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val patients = documentSnapshot.get("patients") as List<String>?
                    if (patients != null) {
                        onPatientsFetched(patients)
                    } else {
                        onPatientsFetched(emptyList())
                    }
                } else {
                    onPatientsFetched(emptyList())
                }
            }
    }*/
}
