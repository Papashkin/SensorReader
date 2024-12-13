package com.example.sensorreader.data.repository

import android.hardware.SensorEventListener

interface SensorRepository {
    fun startCollectingSensorData(sensorType: Int, listener: SensorEventListener)
    fun stopCollectingSensorData(sensorType: Int, listener: SensorEventListener)
    suspend fun setSensorDataToDatabase()
}