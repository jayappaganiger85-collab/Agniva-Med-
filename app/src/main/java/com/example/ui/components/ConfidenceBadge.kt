package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.filled.Warning
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
import com.example.data.model.ConfidenceLevel
import com.example.data.model.VerificationStatus
import com.example.ui.theme.MedicalAmber
import com.example.ui.theme.MedicalAmberBg
import com.example.ui.theme.MedicalEmerald
import com.example.ui.theme.MedicalEmeraldBg
import com.example.ui.theme.MedicalRose
import com.example.ui.theme.MedicalRoseBg

@Composable
fun ConfidenceBadge(
    score: Int,
    verificationStatus: VerificationStatus,
    modifier: Modifier = Modifier
) {
    val (bgColor, borderColor, textColor, icon, label) = when (verificationStatus) {
        VerificationStatus.VERIFIED_HIGH_CONFIDENCE -> {
            Tuple5(
                MedicalEmeraldBg,
                MedicalEmerald.copy(alpha = 0.6f),
                MedicalEmerald,
                Icons.Default.VerifiedUser,
                "$score% Confident"
            )
        }
        VerificationStatus.NEEDS_DOCTOR_VERIFICATION -> {
            Tuple5(
                MedicalAmberBg,
                MedicalAmber.copy(alpha = 0.8f),
                MedicalAmber,
                Icons.Default.Warning,
                "Needs Verification"
            )
        }
        VerificationStatus.UNCLEAR_HANDWRITING -> {
            Tuple5(
                MedicalRoseBg,
                MedicalRose.copy(alpha = 0.8f),
                MedicalRose,
                Icons.Default.Warning,
                "Unclear Shorthand"
            )
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = textColor,
            modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

private data class Tuple5<A, B, C, D, E>(
    val a: A, val b: B, val c: C, val d: D, val e: E
)
