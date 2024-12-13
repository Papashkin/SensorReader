package com.example.sensorreader.data.repository

import android.hardware.SensorEventListener
import com.example.sensorreader.data.model.SensorDataModel

interface SensorRepository {
    fun startCollectingSensorData(sensorType: Int, listener: SensorEventListener)
    fun stopCollectingSensorData(sensorType: Int, listener: SensorEventListener)
    suspend fun setSensorDataToDatabase(data: List<SensorDataModel>)
    suspend fun getSensorDataFromDatabase(): List<SensorDataModel>
}