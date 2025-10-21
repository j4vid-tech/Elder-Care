package com.example.eldercare.model.datastore

import kotlinx.coroutines.flow.Flow

interface PatientUserInfoStore {
    fun patientId(): Flow<String>

    suspend fun savePatientId(name: String)
}