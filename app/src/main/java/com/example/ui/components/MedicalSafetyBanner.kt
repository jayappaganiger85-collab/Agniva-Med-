package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.SupportedLanguage
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.MidnightCard
import com.example.ui.theme.MidnightCardElevated
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun MedicalSafetyBanner(
    language: SupportedLanguage = SupportedLanguage.KANNADA,
    modifier: Modifier = Modifier
) {
    val message = when (language) {
        SupportedLanguage.KANNADA -> "ವೈದ್ಯಕೀಯ ಸುರಕ್ಷತೆ: ಈ ಮಾಹಿತಿಯು ಜಾಗೃತಿಗಾಗಿ ಮಾತ್ರ. ವೈದ್ಯರ ಅಥವಾ ಫಾರ್ಮಸಿಸ್ಟ್ ಅವರ ಅನುಮತಿಯಿಲ್ಲದೆ ಔಷಧಿಗಳನ್ನು ಪ್ರಾರಂಭಿಸಬೇಡಿ ಅಥವಾ ನಿಲ್ಲಿಸಬೇಡಿ. ಅಸ್ಪಷ್ಟ ಕೈಬರಹವನ್ನು ಖಚಿತಪಡಿಸಿಕೊಳ್ಳಿ."
        SupportedLanguage.ENGLISH -> "Medical Safety: AGNIVA MED AI is an assistive reader. Never alter or begin medication without confirming with a registered doctor or pharmacist. Ambiguous handwriting is flagged for verification."
        SupportedLanguage.HINDI -> "चिकित्सा सुरक्षा: यह जानकारी केवल सहायता हेतु है। पंजीकृत डॉक्टर या फार्मासिस्ट से परामर्श के बिना दवा शुरू या बंद न करें। अस्पष्ट लिखावट को सत्यापित अवश्य करें।"
    }

    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MidnightCardElevated.copy(alpha = 0.8f))
            .border(1.dp, ElectricCyan.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
            .padding(14.dp)
    ) {
        Icon(
            imageVector = Icons.Default.HealthAndSafety,
            contentDescription = "Medical Safety",
            tint = ElectricCyan,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = "MEDICAL SAFETY PROTOCOL",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = ElectricCyan,
                letterSpacing = 0.8.sp
            )
            Text(
                text = message,
                fontSize = 12.sp,
                color = TextSecondary,
                lineHeight = 17.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
