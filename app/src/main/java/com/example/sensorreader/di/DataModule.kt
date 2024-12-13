package com.example.sensorreader.di

import android.content.Context
import androidx.room.Room
import com.example.sensorreader.data.local.sensordatabase.SensorsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideSensorsDatabase(@ApplicationContext appContext: Context): SensorsDatabase =
        Room.databaseBuilder(appContext, SensorsDatabase::class.java, "SensorData")
            .build()

    @Singleton
    @Provides
    fun provideSensorDataDao(database: SensorsDatabase) = database.sensorDataDao()
}