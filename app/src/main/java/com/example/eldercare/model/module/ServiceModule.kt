package com.example.eldercare.model.module

import com.example.eldercare.model.service.CaretakerAccountService
import com.example.eldercare.model.service.AuthService
import com.example.eldercare.model.service.impl.CaretakerAccountServiceImpl
import com.example.eldercare.model.service.impl.AuthServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAuthService(impl: AuthServiceImpl): AuthService
    @Binds
    abstract fun provideAccountService(impl: CaretakerAccountServiceImpl): CaretakerAccountService
}
