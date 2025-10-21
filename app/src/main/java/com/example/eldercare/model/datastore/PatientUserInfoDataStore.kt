package com.example.eldercare.model.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val PATIENT_ID = stringPreferencesKey("elder_id")

class PatientUserInfoDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PatientUserInfoStore {
    override fun patientId(): Flow<String> {
        return dataStore.data
            .catch {
                emit(emptyPreferences())
            }
            .map { preference ->
                preference[PATIENT_ID] ?: ""
            }
    }

    override suspend fun savePatientId(name: String) {
        dataStore.edit { preference ->
            preference[PATIENT_ID] = name
        }
    }
}