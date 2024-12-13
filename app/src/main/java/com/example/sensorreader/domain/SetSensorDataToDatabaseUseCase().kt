package com.example.sensorreader.domain

import android.util.Log
import com.example.sensorreader.data.model.SensorDataModel
import com.example.sensorreader.data.repository.SensorRepository
import javax.inject.Inject

class SetSensorDataToDatabaseUseCase @Inject constructor(
    private val repository: SensorRepository
) {
    suspend fun run(data: List<SensorDataModel>) {
        repository.setSensorDataToDatabase(data)
        Log.wtf(this::class.simpleName, "sensor data saved in database successfully")
    }
}