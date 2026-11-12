package com.example.data.repository

import android.content.Context
import android.net.Uri
import com.example.data.ai.PrescriptionAiEngine
import com.example.data.ai.ScanStep
import com.example.data.local.AppDatabase
import com.example.data.model.PrescriptionRecord
import kotlinx.coroutines.flow.Flow

class PrescriptionRepository(context: Context) {

    private val db = AppDatabase.getDatabase(context)
    private val dao = db.prescriptionDao()
    private val aiEngine = PrescriptionAiEngine(context)

    fun getAllPrescriptions(): Flow<List<PrescriptionRecord>> = dao.getAllPrescriptions()

    suspend fun getPrescriptionById(id: String): PrescriptionRecord? = dao.getPrescriptionById(id)

    suspend fun savePrescription(prescription: PrescriptionRecord) {
        dao.insertPrescription(prescription)
    }

    suspend fun deletePrescription(prescription: PrescriptionRecord) {
        dao.deletePrescription(prescription)
    }

    suspend fun deleteById(id: String) {
        dao.deleteById(id)
    }

    suspend fun clearAll() {
        dao.clearAll()
    }

    suspend fun analyzePrescription(
        imageUri: Uri?,
        sampleId: String?,
        onProgress: (ScanStep) -> Unit
    ): PrescriptionRecord {
        val result = aiEngine.analyzePrescription(imageUri, sampleId, onProgress)
        // Auto-save to history
        dao.insertPrescription(result)
        return result
    }
}
