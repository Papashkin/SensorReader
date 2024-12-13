package com.example.sensorreader.data.repository

import android.hardware.SensorEventListener
import com.example.sensorreader.data.SensorDataProvider
import javax.inject.Inject

class SensorRepositoryImpl @Inject constructor(
    private val sensorDataProvider: SensorDataProvider
): SensorRepository {

    override fun startCollectingSensorData(sensorType: Int, listener: SensorEventListener) =
        sensorDataProvider.startCollecting(sensorType, listener)

    override fun stopCollectingSensorData(sensorType: Int, listener: SensorEventListener) =
        sensorDataProvider.stopCollecting(sensorType, listener)

    override suspend fun setSensorDataToDatabase() {
        TODO("Not yet implemented")
    }
}