package com.example.submissiondemo.dataStore

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VitalsDio {
    @Query("SELECT * FROM vitals")
    suspend fun getAllVitals(): List<VitalsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVitals(vitals: VitalsEntity)
}