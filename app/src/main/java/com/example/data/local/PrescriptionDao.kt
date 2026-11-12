package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.PrescriptionRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface PrescriptionDao {

    @Query("SELECT * FROM prescriptions ORDER BY scanDate DESC")
    fun getAllPrescriptions(): Flow<List<PrescriptionRecord>>

    @Query("SELECT * FROM prescriptions WHERE id = :id LIMIT 1")
    suspend fun getPrescriptionById(id: String): PrescriptionRecord?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrescription(prescription: PrescriptionRecord)

    @Delete
    suspend fun deletePrescription(prescription: PrescriptionRecord)

    @Query("DELETE FROM prescriptions WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM prescriptions")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM prescriptions")
    suspend fun getCount(): Int
}
