package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.MedicineDatabaseEntry
import com.example.data.model.SupportedLanguage
import com.example.ui.components.GlowCard
import com.example.ui.components.LanguageSelectorPill
import com.example.ui.components.MedicalSafetyBanner
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MidnightCard
import com.example.ui.theme.MidnightCardElevated
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.MidnightSurface
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.PrescriptionViewModel

@Composable
fun MedicineInfoScreen(
    viewModel: PrescriptionViewModel
) {
    val searchQuery by viewModel.medicineSearchQuery.collectAsState()
    val selectedLang by viewModel.selectedLanguage.collectAsState()
    val entries = viewModel.getFilteredMedicineDatabase()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MidnightNavy)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Top Navigation & Language Pill
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

        Spacer(modifier = Modifier.height(14.dp))

        // Title
        Column {
            Text(
                text = "MEDICINE KNOWLEDGE BASE",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = ElectricCyan,
                letterSpacing = 1.sp
            )
            Text(
                text = when (selectedLang) {
                    SupportedLanguage.KANNADA -> "ಔಷಧಿ ಮಾಹಿತಿ ಮತ್ತು ಸುರಕ್ಷತೆ"
                    SupportedLanguage.HINDI -> "दवा निर्देशिका और सावधानियां"
                    SupportedLanguage.ENGLISH -> "Drug Directory & Pharmacology"
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.setMedicineSearchQuery(it) },
            placeholder = { Text("Search by medicine name, generic molecule...", color = TextMuted, fontSize = 13.sp) },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = ElectricCyan, modifier = Modifier.size(18.dp))
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("medicine_search_input"),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MidnightCard,
                unfocusedContainerColor = MidnightCard,
                focusedBorderColor = ElectricCyan,
                unfocusedBorderColor = MidnightCardElevated,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            shape = RoundedCornerShape(14.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(entries, key = { it.id }) { entry ->
                MedicineExpandableCard(
                    entry = entry,
                    language = selectedLang
                )
            }

            item {
                MedicalSafetyBanner(language = selectedLang)
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
    }
}

@Composable
private fun MedicineExpandableCard(
    entry: MedicineDatabaseEntry,
    language: SupportedLanguage
) {
    var isExpanded by remember { mutableStateOf(false) }

    GlowCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        cornerRadius = 16.dp
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MidnightCardElevated),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Medication,
                            contentDescription = null,
                            tint = ElectricCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = entry.brandName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = entry.genericName,
                            fontSize = 12.sp,
                            color = ElectricBlue
                        )
                    }
                }

                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = "Expand",
                    tint = TextSecondary
                )
            }

            // Category Badge
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MidnightSurface)
                    .border(1.dp, MidnightCardElevated, RoundedCornerShape(6.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = "${entry.category} • ${entry.standardDosageForms}",
                    fontSize = 10.sp,
                    color = TextMuted
                )
            }

            // Summary use
            val uses = when (language) {
                SupportedLanguage.KANNADA -> entry.commonUsesKn
                SupportedLanguage.HINDI -> entry.commonUsesHi
                SupportedLanguage.ENGLISH -> entry.commonUsesEn
            }

            Text(
                text = uses,
                fontSize = 12.sp,
                color = TextSecondary,
                lineHeight = 17.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Expanded Pharmacology & Warnings
            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MidnightCardElevated)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    val directions = when (language) {
                        SupportedLanguage.KANNADA -> entry.directionsKn
                        SupportedLanguage.HINDI -> entry.directionsHi
                        SupportedLanguage.ENGLISH -> entry.directionsEn
                    }

                    val warnings = when (language) {
                        SupportedLanguage.KANNADA -> entry.warningsKn
                        SupportedLanguage.HINDI -> entry.warningsHi
                        SupportedLanguage.ENGLISH -> entry.warningsEn
                    }

                    val sideEffects = when (language) {
                        SupportedLanguage.KANNADA -> entry.sideEffectsKn
                        SupportedLanguage.HINDI -> entry.sideEffectsHi
                        SupportedLanguage.ENGLISH -> entry.sideEffectsEn
                    }

                    SectionDetail(title = "Directions / ಸೇವಿಸುವ ವಿಧಾನ", text = directions, color = ElectricCyan)
                    Spacer(modifier = Modifier.height(6.dp))
                    SectionDetail(title = "Important Warnings / ಪ್ರಮುಖ ಎಚ್ಚರಿಕೆ", text = warnings, color = MedicalAmber)
                    Spacer(modifier = Modifier.height(6.dp))
                    SectionDetail(title = "Possible Side Effects / ಅಡ್ಡಪರಿಣಾಮಗಳು", text = sideEffects, color = TextMuted)

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Source: ${entry.source}",
                        fontSize = 9.sp,
                        color = TextMuted
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionDetail(
    title: String,
    text: String,
    color: Color
) {
    Column {
        Text(
            text = title,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = text,
            fontSize = 12.sp,
            color = TextPrimary,
            lineHeight = 16.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}
