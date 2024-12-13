package com.example.sensorreader.data.repository

import android.hardware.SensorEventListener
import com.example.sensorreader.data.local.sensordatabase.SensorDataDao
import com.example.sensorreader.data.local.sensordataprovider.SensorDataProvider
import com.example.sensorreader.data.model.SensorDataModel
import javax.inject.Inject

class SensorRepositoryImpl @Inject constructor(
    private val sensorDataProvider: SensorDataProvider,
    private val dao: SensorDataDao,
): SensorRepository {

    override fun startCollectingSensorData(sensorType: Int, listener: SensorEventListener) =
        sensorDataProvider.startCollecting(sensorType, listener)

    override fun stopCollectingSensorData(sensorType: Int, listener: SensorEventListener) =
        sensorDataProvider.stopCollecting(sensorType, listener)

    override suspend fun setSensorDataToDatabase(data: List<SensorDataModel>) {
        dao.insertSensorData(data)
    }

    override suspend fun getSensorDataFromDatabase(): List<SensorDataModel> {
        return dao.getAll()
    }
}