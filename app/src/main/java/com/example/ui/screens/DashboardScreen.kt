package com.example.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.UploadFile
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.PrescriptionRecord
import com.example.data.model.SupportedLanguage
import com.example.data.model.VerificationStatus
import com.example.ui.components.ConfidenceBadge
import com.example.ui.components.GlowCard
import com.example.ui.components.LanguageSelectorPill
import com.example.ui.components.MedicalSafetyBanner
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalEmerald
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
fun DashboardScreen(
    viewModel: PrescriptionViewModel
) {
    val selectedLang by viewModel.selectedLanguage.collectAsState()
    val allPrescriptions by viewModel.prescriptions.collectAsState()
    val stats = viewModel.getStats(allPrescriptions)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightNavy)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            // Header: Branding & Language Switcher
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MidnightCardElevated)
                            .border(1.dp, ElectricCyan.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                            .padding(6.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_agniva_logo_1794490578639),
                            contentDescription = "Logo",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "AGNIVA MED AI",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            color = TextPrimary,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = when (selectedLang) {
                                SupportedLanguage.KANNADA -> "ಪ್ರತಿ ಪ್ರಿಸ್ಕ್ರಿಪ್ಷನ್, ಇನ್ನು ಸುಲಭ"
                                SupportedLanguage.HINDI -> "हर पर्चा, अब आसान"
                                SupportedLanguage.ENGLISH -> "Every Prescription, Made Easy."
                            },
                            fontSize = 11.sp,
                            color = ElectricCyan,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                LanguageSelectorPill(
                    currentLanguage = selectedLang,
                    onLanguageSelected = { viewModel.setLanguage(it) }
                )
            }
        }

        // Hero Banner Card
        item {
            GlowCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                hasGlow = true
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_ai_med_1794490596589),
                    contentDescription = "AI Scanner Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // Dark gradient overlay for text readability
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MidnightNavy.copy(alpha = 0.6f),
                                    MidnightNavy.copy(alpha = 0.95f)
                                )
                            )
                        )
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(18.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(ElectricBlue.copy(alpha = 0.85f))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "GEMINI VISION OCR 3.0",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = when (selectedLang) {
                            SupportedLanguage.KANNADA -> "ವೈದ್ಯರ ಪ್ರಿಸ್ಕ್ರಿಪ್ಷನ್ ಸ್ಕ್ಯಾನ್ ಮಾಡಿ"
                            SupportedLanguage.HINDI -> "डॉक्टर का पर्चा स्कैन करें"
                            SupportedLanguage.ENGLISH -> "Decipher Handwritten Rx"
                        },
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = when (selectedLang) {
                            SupportedLanguage.KANNADA -> "ಔಷಧಿಗಳು, ಡೋಸೇಜ್ ಮತ್ತು ಸಮಯವನ್ನು ಕನ್ನಡದಲ್ಲಿ ತಿಳಿಯಿರಿ"
                            SupportedLanguage.HINDI -> "दवाइयों का नाम, खुराक और समय आसानी से समझें"
                            SupportedLanguage.ENGLISH -> "Instant medicine dosage, timing & multi-language explanations"
                        },
                        fontSize = 12.sp,
                        color = TextSecondary,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }

        // Primary Action Buttons Row
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        viewModel.resetScannerState()
                        viewModel.navigateTo(Screen.SCANNER)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp)
                        .testTag("upload_prescription_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = ElectricBlue),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DocumentScanner,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when (selectedLang) {
                            SupportedLanguage.KANNADA -> "ಸ್ಕ್ಯಾನ್ ಮಾಡಿ"
                            SupportedLanguage.HINDI -> "स्कैन करें"
                            SupportedLanguage.ENGLISH -> "Scan Rx Now"
                        },
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Button(
                    onClick = {
                        viewModel.navigateTo(Screen.MEDICINE_INFO)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp)
                        .testTag("medicine_guide_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = MidnightCardElevated),
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.5f))
                ) {
                    Icon(
                        imageVector = Icons.Default.Medication,
                        contentDescription = null,
                        tint = ElectricCyan,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when (selectedLang) {
                            SupportedLanguage.KANNADA -> "ಔಷಧಿ ಮಾಹಿತಿ"
                            SupportedLanguage.HINDI -> "दवा निर्देशिका"
                            SupportedLanguage.ENGLISH -> "Drug Guide"
                        },
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = ElectricCyan
                    )
                }
            }
        }

        // AI Processing Statistics Grid
        item {
            Column {
                Text(
                    text = "AI PROCESSING PERFORMANCE",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = ElectricCyan,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    StatCard(
                        title = "Scans Analyzed",
                        value = "${stats.totalScans}",
                        icon = Icons.Default.UploadFile,
                        accentColor = ElectricCyan,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Meds Decoded",
                        value = "${stats.totalMedicinesDecoded}",
                        icon = Icons.Default.Medication,
                        accentColor = ElectricBlue,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Avg Accuracy",
                        value = "${stats.averageAccuracy}%",
                        icon = Icons.Default.CheckCircle,
                        accentColor = MedicalEmerald,
                        modifier = Modifier.weight(1f)
                    )
                    StatCard(
                        title = "Safety Flags",
                        value = "${stats.safetyFlags}",
                        icon = Icons.Default.Shield,
                        accentColor = MedicalAmber,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        // Quick Demo Prescription Carousel / Selector
        item {
            GlowCard(
                modifier = Modifier.fillMaxWidth(),
                hasGlow = false
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = ElectricCyan,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Interactive Prescriptions Demo",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        }
                        Text(
                            text = "Tap to test",
                            fontSize = 12.sp,
                            color = ElectricCyan
                        )
                    }

                    Text(
                        text = "Instant 1-tap test with authentic handwriting samples:",
                        fontSize = 12.sp,
                        color = TextSecondary,
                        modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        DemoPrescriptionRow(
                            title = "Dr. R. Sharma (MBBS MD) - 3 Medicines",
                            subtitle = "Amoxicillin, Paracetamol, Pantoprazole (High confidence)",
                            tag = "Clinic Rx",
                            onClick = {
                                viewModel.selectSamplePrescription("rx_sample_sharma")
                                viewModel.navigateTo(Screen.SCANNER)
                            }
                        )
                        DemoPrescriptionRow(
                            title = "Dr. Ananya Rao (Cardiology) - Apollo",
                            subtitle = "Metformin, Atorvastatin, Losartan (High confidence)",
                            tag = "Cardio Rx",
                            onClick = {
                                viewModel.selectSamplePrescription("rx_sample_rao")
                                viewModel.navigateTo(Screen.SCANNER)
                            }
                        )
                        DemoPrescriptionRow(
                            title = "Unclear Handwriting - Safety Alert Demo",
                            subtitle = "Shows strict medical safety flag & pharmacist review",
                            tag = "Safety Test",
                            tagColor = MedicalAmber,
                            onClick = {
                                viewModel.selectSamplePrescription("rx_sample_uncertain")
                                viewModel.navigateTo(Screen.SCANNER)
                            }
                        )
                    }
                }
            }
        }

        // Recent Prescription History
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "RECENT PRESCRIPTIONS",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = ElectricCyan,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "View All",
                    fontSize = 12.sp,
                    color = ElectricBlue,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.clickable { viewModel.navigateTo(Screen.HISTORY) }
                )
            }
        }

        items(allPrescriptions.take(3)) { prescription ->
            RecentPrescriptionCard(
                prescription = prescription,
                onClick = { viewModel.viewPrescriptionDetails(prescription) }
            )
        }

        // Medical Safety Banner
        item {
            MedicalSafetyBanner(language = selectedLang)
            Spacer(modifier = Modifier.height(80.dp)) // Space for bottom bar
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .background(MidnightCardElevated)
            .border(1.dp, accentColor.copy(alpha = 0.25f), RoundedCornerShape(14.dp))
            .padding(vertical = 12.dp, horizontal = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                text = title,
                fontSize = 9.sp,
                color = TextSecondary,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun DemoPrescriptionRow(
    title: String,
    subtitle: String,
    tag: String,
    tagColor: Color = ElectricCyan,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MidnightSurface)
            .border(1.dp, tagColor.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(tagColor.copy(alpha = 0.15f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = tag,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = tagColor
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = title,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    maxLines = 1
                )
            }
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = TextSecondary,
                maxLines = 1,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Test sample",
            tint = tagColor,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
private fun RecentPrescriptionCard(
    prescription: PrescriptionRecord,
    onClick: () -> Unit
) {
    val dateFormat = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault())
    val dateString = dateFormat.format(Date(prescription.scanDate))

    GlowCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        cornerRadius = 16.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MidnightCardElevated)
                    .border(1.dp, ElectricCyan.copy(alpha = 0.3f), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.DocumentScanner,
                    contentDescription = null,
                    tint = ElectricCyan,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = prescription.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    maxLines = 1
                )
                Text(
                    text = "${prescription.doctorName ?: "Doctor"} • $dateString",
                    fontSize = 11.sp,
                    color = TextMuted,
                    maxLines = 1
                )
                Text(
                    text = "${prescription.medicines.size} Medicines Decoded",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = ElectricBlue,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            ConfidenceBadge(
                score = prescription.overallConfidence,
                verificationStatus = if (prescription.hasUncertainWords) VerificationStatus.NEEDS_DOCTOR_VERIFICATION else VerificationStatus.VERIFIED_HIGH_CONFIDENCE
            )
        }
    }
}
