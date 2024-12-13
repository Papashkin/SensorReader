package com.example.sensorreader.data.repository

interface SensorRepository {
    suspend fun startCollectingSensorData()
    suspend fun stopCollectingSensorData()
    suspend fun setSensorDataToDatabase()
}