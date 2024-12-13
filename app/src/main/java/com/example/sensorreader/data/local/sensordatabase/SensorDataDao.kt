package com.example.sensorreader.data.local.sensordatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.sensorreader.data.model.SensorDataModel

@Dao
interface SensorDataDao {

    @Query("SELECT * from sensordatamodel")
    abstract suspend fun getAll(): List<SensorDataModel>

    @Insert
    abstract suspend fun insertSensorData(sensorData: List<SensorDataModel>)
}
