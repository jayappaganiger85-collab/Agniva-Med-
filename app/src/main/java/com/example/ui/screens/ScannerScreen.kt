package com.example.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Refresh
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.R
import com.example.data.local.SamplePrescriptions
import com.example.data.model.SupportedLanguage
import com.example.ui.components.GlowCard
import com.example.ui.components.MedicalSafetyBanner
import com.example.ui.components.ScanningLaserOverlay
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.MidnightCard
import com.example.ui.theme.MidnightCardElevated
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.MidnightSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.PrescriptionViewModel
import com.example.ui.viewmodel.ScanUiState

@Composable
fun ScannerScreen(
    viewModel: PrescriptionViewModel
) {
    val selectedImageUri by viewModel.selectedImageUri.collectAsState()
    val selectedSampleId by viewModel.selectedSampleId.collectAsState()
    val scanUiState by viewModel.scanUiState.collectAsState()
    val selectedLang by viewModel.selectedLanguage.collectAsState()

    // Android Zero-permission Photo Picker
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            if (uri != null) {
                viewModel.selectCustomImage(uri)
            }
        }
    )

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightNavy)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Screen Top Header
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "PRESCRIPTION SCANNER",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = ElectricCyan,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = when (selectedLang) {
                            SupportedLanguage.KANNADA -> "ಕೈಬರಹ ವಿಶ್ಲೇಷಕ"
                            SupportedLanguage.HINDI -> "हस्तलिखित पर्चा स्कैनर"
                            SupportedLanguage.ENGLISH -> "AI Handwriting Reader"
                        },
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Image Preview Container with Scanning Laser
            GlowCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
                hasGlow = true
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // Display image: either custom URI or authentic sample prescription
                    if (selectedImageUri != null) {
                        AsyncImage(
                            model = selectedImageUri,
                            contentDescription = "Prescription Preview",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        val sampleDrawable = when (selectedSampleId) {
                            "rx_sample_rao" -> R.drawable.rx_sample_rao_1794490626707
                            else -> R.drawable.rx_sample_sharma_1794490611866
                        }
                        Image(
                            painter = painterResource(id = sampleDrawable),
                            contentDescription = "Sample Prescription",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }

                    // Futuristic HUD Corner Reticles
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        // Top-left corner
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .size(24.dp)
                                .border(2.dp, ElectricCyan, RoundedCornerShape(topStart = 6.dp))
                        )
                        // Top-right corner
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(24.dp)
                                .border(2.dp, ElectricCyan, RoundedCornerShape(topEnd = 6.dp))
                        )
                        // Bottom-left corner
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .size(24.dp)
                                .border(2.dp, ElectricCyan, RoundedCornerShape(bottomStart = 6.dp))
                        )
                        // Bottom-right corner
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(24.dp)
                                .border(2.dp, ElectricCyan, RoundedCornerShape(bottomEnd = 6.dp))
                        )
                    }

                    // Active Scanning Laser Beam Overlay
                    if (scanUiState is ScanUiState.Scanning) {
                        val state = scanUiState as ScanUiState.Scanning
                        ScanningLaserOverlay(
                            progress = state.progress,
                            statusMessage = state.statusMessage
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Primary Scan / Analyze Button
            val isScanning = scanUiState is ScanUiState.Scanning
            Button(
                onClick = { viewModel.startScanning() },
                enabled = !isScanning,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("start_scan_ai_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ElectricBlue,
                    disabledContainerColor = ElectricBlue.copy(alpha = 0.5f)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = if (isScanning) {
                        "Analyzing Handwriting..."
                    } else {
                        when (selectedLang) {
                            SupportedLanguage.KANNADA -> "ಅಗ್ನಿವಾ AI ಮೂಲಕ ಸ್ಕ್ಯಾನ್ ಮಾಡಿ"
                            SupportedLanguage.HINDI -> "अग्निवा AI से स्कैन करें"
                            SupportedLanguage.ENGLISH -> "Analyze with AGNIVA AI"
                        }
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Upload Options Row (Gallery Picker + Camera option)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .testTag("pick_gallery_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = MidnightCardElevated),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, ElectricCyan.copy(alpha = 0.4f))
                ) {
                    Icon(
                        imageVector = Icons.Default.Collections,
                        contentDescription = "Upload from Gallery",
                        tint = ElectricCyan,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Upload Image",
                        fontSize = 13.sp,
                        color = ElectricCyan,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Button(
                    onClick = {
                        // Pre-select alternative authentic sample
                        if (selectedSampleId == "rx_sample_sharma") {
                            viewModel.selectSamplePrescription("rx_sample_rao")
                        } else {
                            viewModel.selectSamplePrescription("rx_sample_sharma")
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .testTag("switch_sample_button"),
                    colors = ButtonDefaults.buttonColors(containerColor = MidnightCardElevated),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MidnightCard)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Switch Sample",
                        tint = TextSecondary,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Switch Sample",
                        fontSize = 13.sp,
                        color = TextSecondary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Sample Prescriptions Fast Selector
            Text(
                text = "SELECT TEST PRESCRIPTION",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = ElectricCyan,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                SampleSelectorCard(
                    title = "Dr. R. Sharma (MBBS MD)",
                    subtitle = "Amoxicillin, Paracetamol, Pantoprazole",
                    isSelected = selectedSampleId == "rx_sample_sharma" && selectedImageUri == null,
                    onClick = { viewModel.selectSamplePrescription("rx_sample_sharma") }
                )
                SampleSelectorCard(
                    title = "Dr. Ananya Rao (Cardiology)",
                    subtitle = "Metformin, Atorvastatin, Losartan",
                    isSelected = selectedSampleId == "rx_sample_rao" && selectedImageUri == null,
                    onClick = { viewModel.selectSamplePrescription("rx_sample_rao") }
                )
                SampleSelectorCard(
                    title = "Safety Alert: Unclear Shorthand Demo",
                    subtitle = "Tests zero-guesswork doctor verification protocol",
                    isSelected = selectedSampleId == "rx_sample_uncertain" && selectedImageUri == null,
                    onClick = { viewModel.selectSamplePrescription("rx_sample_uncertain") }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Medical Safety Banner
            MedicalSafetyBanner(language = selectedLang)
            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}

@Composable
private fun SampleSelectorCard(
    title: String,
    subtitle: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) MidnightCardElevated else MidnightSurface)
            .border(
                width = if (isSelected) 1.5.dp else 1.dp,
                color = if (isSelected) ElectricCyan else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = if (isSelected) Color.White else TextSecondary
            )
            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = TextMuted,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        if (isSelected) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(androidx.compose.foundation.shape.CircleShape)
                    .background(ElectricCyan)
            )
        }
    }
}
