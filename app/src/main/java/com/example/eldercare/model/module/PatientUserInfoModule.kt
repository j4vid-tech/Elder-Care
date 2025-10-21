package com.example.eldercare.model.module

import com.example.eldercare.model.datastore.PatientUserInfoDataStore
import com.example.eldercare.model.datastore.PatientUserInfoStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PatientUserInfoModule {
    @Binds
    abstract fun providePatientUserInfoStore(impl: PatientUserInfoDataStore): PatientUserInfoStore
}