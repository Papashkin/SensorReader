package com.example.sensorreader.data.repository

import javax.inject.Inject

class SensorRepositoryImpl @Inject constructor(
): SensorRepository {

    override suspend fun startCollectingSensorData() {
        TODO("Not yet implemented")
    }

    override suspend fun stopCollectingSensorData() {
        TODO("Not yet implemented")
    }

    override suspend fun setSensorDataToDatabase() {
        TODO("Not yet implemented")
    }
}