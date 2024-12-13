package com.example.sensorreader.di

import android.content.Context
import android.hardware.SensorManager
import com.example.sensorreader.data.SensorDataProvider
import com.example.sensorreader.data.SensorDataProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object SensorModule {

    @Provides
    fun provideSensorManager(@ApplicationContext context: Context): SensorManager {
        return context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    @Provides
    fun provideSensorDataProvider(
        sensorManager: SensorManager
    ): SensorDataProvider = SensorDataProviderImpl(sensorManager)
}