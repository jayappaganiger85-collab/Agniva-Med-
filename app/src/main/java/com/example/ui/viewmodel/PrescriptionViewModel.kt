package com.example.ui.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ai.ScanStep
import com.example.data.local.MedicineKnowledgeBase
import com.example.data.local.SamplePrescriptions
import com.example.data.model.MedicineDatabaseEntry
import com.example.data.model.PrescriptionRecord
import com.example.data.model.SupportedLanguage
import com.example.data.repository.PrescriptionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class Screen {
    SPLASH,
    DASHBOARD,
    SCANNER,
    RESULT,
    HISTORY,
    MEDICINE_INFO,
    SETTINGS
}

sealed interface ScanUiState {
    data object Idle : ScanUiState
    data class Scanning(val progress: Float, val statusMessage: String) : ScanUiState
    data class Success(val prescription: PrescriptionRecord) : ScanUiState
    data class Error(val message: String) : ScanUiState
}

data class DashboardStats(
    val totalScans: Int,
    val totalMedicinesDecoded: Int,
    val averageAccuracy: Int,
    val safetyFlags: Int
)

class PrescriptionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PrescriptionRepository(application)

    // Navigation state
    private val _currentScreen = MutableStateFlow(Screen.SPLASH)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val screenBackStack = mutableListOf<Screen>()

    // Selected Language
    private val _selectedLanguage = MutableStateFlow(SupportedLanguage.KANNADA)
    val selectedLanguage: StateFlow<SupportedLanguage> = _selectedLanguage.asStateFlow()

    // Dark Mode (defaults to true for premium midnight aesthetic)
    private val _isDarkMode = MutableStateFlow(true)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    // All Prescriptions Flow from Room
    val prescriptions: StateFlow<List<PrescriptionRecord>> = repository.getAllPrescriptions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SamplePrescriptions.allSamples
        )

    // Currently viewed prescription in Result Screen
    private val _selectedPrescription = MutableStateFlow<PrescriptionRecord?>(SamplePrescriptions.sampleSharma)
    val selectedPrescription: StateFlow<PrescriptionRecord?> = _selectedPrescription.asStateFlow()

    // Scanning UI State
    private val _scanUiState = MutableStateFlow<ScanUiState>(ScanUiState.Idle)
    val scanUiState: StateFlow<ScanUiState> = _scanUiState.asStateFlow()

    // Selected input for scanner (Uri or Sample)
    private val _selectedImageUri = MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> = _selectedImageUri.asStateFlow()

    private val _selectedSampleId = MutableStateFlow<String?>("rx_sample_sharma")
    val selectedSampleId: StateFlow<String?> = _selectedSampleId.asStateFlow()

    // Medicine Knowledge Base search query
    private val _medicineSearchQuery = MutableStateFlow("")
    val medicineSearchQuery: StateFlow<String> = _medicineSearchQuery.asStateFlow()

    fun navigateTo(screen: Screen) {
        if (_currentScreen.value != screen) {
            screenBackStack.add(_currentScreen.value)
            _currentScreen.value = screen
        }
    }

    fun navigateBack(): Boolean {
        if (screenBackStack.isNotEmpty()) {
            _currentScreen.value = screenBackStack.removeAt(screenBackStack.size - 1)
            return true
        } else if (_currentScreen.value != Screen.DASHBOARD) {
            _currentScreen.value = Screen.DASHBOARD
            return true
        }
        return false
    }

    fun setLanguage(language: SupportedLanguage) {
        _selectedLanguage.value = language
    }

    fun toggleDarkMode() {
        _isDarkMode.value = !_isDarkMode.value
    }

    fun selectSamplePrescription(sampleId: String) {
        _selectedSampleId.value = sampleId
        _selectedImageUri.value = null
    }

    fun selectCustomImage(uri: Uri) {
        _selectedImageUri.value = uri
        _selectedSampleId.value = null
    }

    fun startScanning() {
        viewModelScope.launch {
            _scanUiState.value = ScanUiState.Scanning(0.05f, "Initializing AGNIVA Neural Scanner...")
            try {
                val record = repository.analyzePrescription(
                    imageUri = _selectedImageUri.value,
                    sampleId = _selectedSampleId.value
                ) { step: ScanStep ->
                    _scanUiState.value = ScanUiState.Scanning(step.progress, step.message)
                }
                _selectedPrescription.value = record
                _scanUiState.value = ScanUiState.Success(record)
                // Navigate to result screen
                navigateTo(Screen.RESULT)
            } catch (e: Exception) {
                _scanUiState.value = ScanUiState.Error(e.localizedMessage ?: "Scanning failed. Please try again.")
            }
        }
    }

    fun resetScannerState() {
        _scanUiState.value = ScanUiState.Idle
    }

    fun viewPrescriptionDetails(prescription: PrescriptionRecord) {
        _selectedPrescription.value = prescription
        navigateTo(Screen.RESULT)
    }

    fun deletePrescription(prescription: PrescriptionRecord) {
        viewModelScope.launch {
            repository.deletePrescription(prescription)
        }
    }

    fun setMedicineSearchQuery(query: String) {
        _medicineSearchQuery.value = query
    }

    fun getFilteredMedicineDatabase(): List<MedicineDatabaseEntry> {
        val q = _medicineSearchQuery.value.trim().lowercase()
        if (q.isBlank()) return MedicineKnowledgeBase.entries
        return MedicineKnowledgeBase.entries.filter {
            it.brandName.lowercase().contains(q) ||
            it.genericName.lowercase().contains(q) ||
            it.category.lowercase().contains(q) ||
            it.commonUsesKn.contains(q) ||
            it.commonUsesEn.lowercase().contains(q)
        }
    }

    fun getStats(records: List<PrescriptionRecord>): DashboardStats {
        val totalScans = records.size
        val totalMeds = records.sumOf { it.medicines.size }
        val avgAcc = if (records.isNotEmpty()) records.map { it.overallConfidence }.average().toInt() else 95
        val safetyFlags = records.count { it.hasUncertainWords }
        return DashboardStats(
            totalScans = totalScans,
            totalMedicinesDecoded = totalMeds,
            averageAccuracy = avgAcc,
            safetyFlags = safetyFlags
        )
    }
}
