package com.example.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalPharmacy
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MedicineItem
import com.example.data.model.PrescriptionRecord
import com.example.data.model.SupportedLanguage
import com.example.data.model.VerificationStatus
import com.example.ui.components.ConfidenceBadge
import com.example.ui.components.GlowCard
import com.example.ui.components.LanguageSelectorPill
import com.example.ui.components.MedicalSafetyBanner
import com.example.ui.theme.CyanBright
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalEmerald
import com.example.ui.theme.MedicalRose
import com.example.ui.theme.MidnightCard
import com.example.ui.theme.MidnightCardElevated
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.MidnightSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.PrescriptionViewModel
import com.example.ui.viewmodel.Screen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ResultScreen(
    viewModel: PrescriptionViewModel
) {
    val context = LocalContext.current
    val prescription by viewModel.selectedPrescription.collectAsState()
    val selectedLang by viewModel.selectedLanguage.collectAsState()

    val rx = prescription

    if (rx == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MidnightNavy),
            contentAlignment = Alignment.Center
        ) {
            Text("No prescription selected", color = TextPrimary)
        }
        return
    }

    val dateFormat = SimpleDateFormat("dd MMMM yyyy, hh:mm a", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(rx.scanDate))

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightNavy)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            // Top Navigation & Language Switcher
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { viewModel.navigateBack() },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(MidnightCard)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextPrimary
                    )
                }

                LanguageSelectorPill(
                    currentLanguage = selectedLang,
                    onLanguageSelected = { viewModel.setLanguage(it) }
                )
            }
        }

        // Prescription Metadata Overview Card
        item {
            GlowCard(
                modifier = Modifier.fillMaxWidth(),
                hasGlow = true
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = rx.title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                            Text(
                                text = "${rx.doctorName ?: "Doctor"} • ${rx.clinicOrHospital ?: "Clinic"}",
                                fontSize = 13.sp,
                                color = ElectricCyan,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                            if (!rx.patientName.isNullOrBlank()) {
                                Text(
                                    text = "Patient: ${rx.patientName}",
                                    fontSize = 12.sp,
                                    color = TextSecondary,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                        ConfidenceBadge(
                            score = rx.overallConfidence,
                            verificationStatus = if (rx.hasUncertainWords) VerificationStatus.NEEDS_DOCTOR_VERIFICATION else VerificationStatus.VERIFIED_HIGH_CONFIDENCE
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MidnightCardElevated)
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Scanned: $formattedDate",
                            fontSize = 11.sp,
                            color = TextMuted
                        )
                        Text(
                            text = "${rx.medicines.size} Medicines Extracted",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = ElectricBlue
                        )
                    }
                }
            }
        }

        // General Advice Banner in Selected Language
        item {
            val adviceText = when (selectedLang) {
                SupportedLanguage.KANNADA -> rx.generalAdviceKn
                SupportedLanguage.HINDI -> rx.generalAdviceHi
                SupportedLanguage.ENGLISH -> rx.generalAdviceEn
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MidnightCardElevated)
                    .border(1.dp, ElectricCyan.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = ElectricCyan,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = when (selectedLang) {
                                SupportedLanguage.KANNADA -> "ವೈದ್ಯರ ಸಾಮಾನ್ಯ ಸಲಹೆಗಳು"
                                SupportedLanguage.HINDI -> "डॉक्टर के सामान्य निर्देश"
                                SupportedLanguage.ENGLISH -> "Doctor's General Advice"
                            },
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = ElectricCyan,
                            letterSpacing = 0.5.sp
                        )
                    }
                    Text(
                        text = adviceText,
                        fontSize = 13.sp,
                        color = TextPrimary,
                        lineHeight = 19.sp,
                        modifier = Modifier.padding(top = 6.dp)
                    )
                }
            }
        }

        // Decoded Medicines Header
        item {
            Text(
                text = when (selectedLang) {
                    SupportedLanguage.KANNADA -> "ವಿಶ್ಲೇಷಿಸಲಾದ ಔಷಧಗಳು (${rx.medicines.size})"
                    SupportedLanguage.HINDI -> "पहचानी गई दवाइयां (${rx.medicines.size})"
                    SupportedLanguage.ENGLISH -> "DECODED MEDICATIONS (${rx.medicines.size})"
                },
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = ElectricCyan,
                letterSpacing = 1.sp
            )
        }

        // Medicine Cards List
        items(rx.medicines) { medicine ->
            MedicineResultCard(
                medicine = medicine,
                language = selectedLang
            )
        }

        // Action Buttons Row (Share / New Scan)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        Toast.makeText(context, "Prescription saved to AGNIVA Health History", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .testTag("save_history_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = MidnightCardElevated),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.4f))
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = ElectricCyan,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Save Record",
                        fontSize = 13.sp,
                        color = ElectricCyan,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        viewModel.resetScannerState()
                        viewModel.navigateTo(Screen.SCANNER)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .testTag("scan_another_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = "Scan Another",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        // Medical Safety Protocol Banner
        item {
            MedicalSafetyBanner(language = selectedLang)
            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}

@Composable
private fun MedicineResultCard(
    medicine: MedicineItem,
    language: SupportedLanguage
) {
    val isUnclear = medicine.verificationStatus != VerificationStatus.VERIFIED_HIGH_CONFIDENCE

    GlowCard(
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 18.dp,
        hasGlow = isUnclear,
        glowColor = if (isUnclear) MedicalAmber else ElectricCyan
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Card Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = medicine.name,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = "Generic: ${medicine.genericName}",
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }
                ConfidenceBadge(
                    score = medicine.confidenceScore,
                    verificationStatus = medicine.verificationStatus
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Original Handwritten Snippet Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .background(MidnightSurface)
                    .border(1.dp, MidnightCardElevated, RoundedCornerShape(10.dp))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Handwriting: ",
                        fontSize = 11.sp,
                        color = TextMuted,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "\"${medicine.originalHandwrittenSnippet}\"",
                        fontSize = 12.sp,
                        color = ElectricCyan,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                }
            }

            // Warning Notice if uncertain
            if (isUnclear && !medicine.needsVerificationWarning.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(MedicalAmber.copy(alpha = 0.15f))
                        .border(1.dp, MedicalAmber.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Alert",
                            tint = MedicalAmber,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = medicine.needsVerificationWarning,
                            fontSize = 11.sp,
                            color = MedicalAmber,
                            lineHeight = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Dosage & Timing Pills Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoChip(
                    icon = Icons.Default.Medication,
                    label = "Dosage",
                    value = medicine.dosage,
                    modifier = Modifier.weight(1f)
                )
                InfoChip(
                    icon = Icons.Default.AccessTime,
                    label = "Frequency",
                    value = medicine.frequency,
                    modifier = Modifier.weight(1f)
                )
                InfoChip(
                    icon = Icons.Default.CalendarToday,
                    label = "Duration",
                    value = medicine.duration,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Multilingual Purpose & Explanation Card (Kannada / Hindi / English)
            val purpose = when (language) {
                SupportedLanguage.KANNADA -> medicine.purposeSummaryKn
                SupportedLanguage.HINDI -> medicine.purposeSummaryHi
                SupportedLanguage.ENGLISH -> medicine.purposeSummaryEn
            }

            val precautions = when (language) {
                SupportedLanguage.KANNADA -> medicine.precautionsKn
                SupportedLanguage.HINDI -> medicine.precautionsHi
                SupportedLanguage.ENGLISH -> medicine.precautionsEn
            }

            val sideEffects = when (language) {
                SupportedLanguage.KANNADA -> medicine.sideEffectsKn
                SupportedLanguage.HINDI -> medicine.sideEffectsHi
                SupportedLanguage.ENGLISH -> medicine.sideEffectsEn
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MidnightCardElevated)
                    .padding(12.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(ElectricBlue.copy(alpha = 0.3f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = language.nativeName,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = ElectricCyan
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = when (language) {
                                SupportedLanguage.KANNADA -> "ಔಷಧಿಯ ವಿವರಣೆ ಮತ್ತು ಉಪಯೋಗ"
                                SupportedLanguage.HINDI -> "दवा का विवरण और उपयोग"
                                SupportedLanguage.ENGLISH -> "Usage & Directions"
                            },
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSecondary
                        )
                    }

                    Text(
                        text = purpose,
                        fontSize = 13.sp,
                        color = TextPrimary,
                        lineHeight = 18.sp,
                        modifier = Modifier.padding(top = 6.dp)
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "• ${when (language) {
                            SupportedLanguage.KANNADA -> "ಸೇವಿಸುವ ವಿಧಾನ: "
                            SupportedLanguage.HINDI -> "निर्देश: "
                            SupportedLanguage.ENGLISH -> "Instructions: "
                        }}${medicine.timing}",
                        fontSize = 12.sp,
                        color = ElectricCyan,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = "• ${when (language) {
                            SupportedLanguage.KANNADA -> "ಮುನ್ನೆಚ್ಚರಿಕೆ: "
                            SupportedLanguage.HINDI -> "सावधानी: "
                            SupportedLanguage.ENGLISH -> "Precautions: "
                        }}$precautions",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Text(
                        text = "• ${when (language) {
                            SupportedLanguage.KANNADA -> "ಅಡ್ಡಪರಿಣಾಮಗಳು: "
                            SupportedLanguage.HINDI -> "दुष्प्रभाव: "
                            SupportedLanguage.ENGLISH -> "Side Effects: "
                        }}$sideEffects",
                        fontSize = 11.sp,
                        color = TextMuted,
                        lineHeight = 15.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(MidnightSurface)
            .border(1.dp, MidnightCardElevated, RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = label,
                fontSize = 9.sp,
                color = TextMuted,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = value,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                maxLines = 1,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}
