package com.example.eldercare.model.module

import com.example.eldercare.model.Caretaker
import com.example.eldercare.model.Meal
import com.example.eldercare.model.Patient
import com.example.eldercare.model.repository.impl.MealRepository
import com.example.eldercare.model.repository.Repository
import com.example.eldercare.model.repository.impl.CaretakerRepository
import com.example.eldercare.model.repository.impl.PatientRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMealRepository(impl: MealRepository): Repository<Meal>
    @Binds
    abstract fun provideCaretakerRepository(impl: CaretakerRepository): Repository<Caretaker>
    @Binds
    abstract fun providePatientRepository(impl: PatientRepository): Repository<Patient>
}
