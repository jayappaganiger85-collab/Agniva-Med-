package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

enum class VerificationStatus {
    VERIFIED_HIGH_CONFIDENCE,
    NEEDS_DOCTOR_VERIFICATION,
    UNCLEAR_HANDWRITING
}

enum class ConfidenceLevel {
    HIGH,     // > 90%
    MODERATE, // 75% - 90%
    LOW       // < 75% (Flagged for pharmacist review)
}

enum class SupportedLanguage(val code: String, val displayName: String, val nativeName: String) {
    KANNADA("kn", "Kannada", "ಕನ್ನಡ"),
    ENGLISH("en", "English", "English"),
    HINDI("hi", "Hindi", "हिन्दी")
}

data class MedicineItem(
    val id: String,
    val name: String,
    val genericName: String,
    val dosage: String,
    val frequency: String,
    val timing: String, // e.g., "After food / ಊಟದ ನಂತರ"
    val duration: String, // e.g., "5 days / ೫ ದಿನಗಳು"
    val originalHandwrittenSnippet: String,
    val confidenceScore: Int, // 0 - 100
    val confidenceLevel: ConfidenceLevel,
    val verificationStatus: VerificationStatus,
    val needsVerificationWarning: String? = null,
    val purposeSummaryEn: String,
    val purposeSummaryKn: String,
    val purposeSummaryHi: String,
    val precautionsEn: String,
    val precautionsKn: String,
    val precautionsHi: String,
    val sideEffectsEn: String,
    val sideEffectsKn: String,
    val sideEffectsHi: String
)

@Entity(tableName = "prescriptions")
@TypeConverters(PrescriptionConverters::class)
data class PrescriptionRecord(
    @PrimaryKey val id: String,
    val title: String,
    val doctorName: String?,
    val clinicOrHospital: String?,
    val patientName: String?,
    val scanDate: Long = System.currentTimeMillis(),
    val imageUri: String?,
    val sampleDrawableResId: Int? = null,
    val medicines: List<MedicineItem>,
    val generalAdviceEn: String,
    val generalAdviceKn: String,
    val generalAdviceHi: String,
    val overallConfidence: Int,
    val hasUncertainWords: Boolean,
    val notes: String = ""
)

class PrescriptionConverters {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val type = Types.newParameterizedType(List::class.java, MedicineItem::class.java)
    private val adapter = moshi.adapter<List<MedicineItem>>(type)

    @TypeConverter
    fun fromMedicineList(list: List<MedicineItem>?): String {
        return if (list == null) "[]" else adapter.toJson(list)
    }

    @TypeConverter
    fun toMedicineList(json: String?): List<MedicineItem> {
        return if (json.isNullOrBlank()) emptyList() else adapter.fromJson(json) ?: emptyList()
    }
}
