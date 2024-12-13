package com.example.sensorreader.di

import com.example.sensorreader.data.repository.SensorRepository
import com.example.sensorreader.data.repository.SensorRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsSensorRepository(sensorRepositoryImpl: SensorRepositoryImpl): SensorRepository

}