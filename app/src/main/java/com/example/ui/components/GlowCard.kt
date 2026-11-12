package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.MidnightBorder
import com.example.ui.theme.MidnightCard

@Composable
fun GlowCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 20.dp,
    hasGlow: Boolean = false,
    glowColor: Color = ElectricCyan,
    backgroundColor: Color = MidnightCard,
    content: @Composable BoxScope.() -> Unit
) {
    val borderBrush = if (hasGlow) {
        Brush.linearGradient(
            colors = listOf(
                glowColor.copy(alpha = 0.8f),
                ElectricBlue.copy(alpha = 0.4f),
                glowColor.copy(alpha = 0.1f)
            )
        )
    } else {
        Brush.linearGradient(
            colors = listOf(
                MidnightBorder.copy(alpha = 0.8f),
                MidnightBorder.copy(alpha = 0.3f)
            )
        )
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        backgroundColor.copy(alpha = 0.95f),
                        backgroundColor.copy(alpha = 0.85f)
                    )
                )
            )
            .border(
                width = if (hasGlow) 1.5.dp else 1.dp,
                brush = borderBrush,
                shape = RoundedCornerShape(cornerRadius)
            )
    ) {
        content()
    }
}
