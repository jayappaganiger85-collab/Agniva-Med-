package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary

@Composable
fun ScanningLaserOverlay(
    progress: Float,
    statusMessage: String,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "laser_anim")
    val laserY by infiniteTransition.animateFloat(
        initialValue = 0.05f,
        targetValue = 0.95f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "laser_y"
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glow_pulse"
    )

    Box(modifier = modifier.fillMaxSize()) {
        // Canvas drawing the scanning laser, beam trail, and detection target markers
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            // 1. Holographic grid lines
            val gridStep = 40.dp.toPx()
            for (x in 0..(canvasWidth / gridStep).toInt()) {
                val xPos = x * gridStep
                drawLine(
                    color = ElectricCyan.copy(alpha = 0.04f),
                    start = Offset(xPos, 0f),
                    end = Offset(xPos, canvasHeight),
                    strokeWidth = 1f
                )
            }
            for (y in 0..(canvasHeight / gridStep).toInt()) {
                val yPos = y * gridStep
                drawLine(
                    color = ElectricCyan.copy(alpha = 0.04f),
                    start = Offset(0f, yPos),
                    end = Offset(canvasWidth, yPos),
                    strokeWidth = 1f
                )
            }

            // 2. Simulated OCR detection bounding boxes appearing at distinct positions
            val currentBeamY = laserY * canvasHeight

            // Box 1 (Rx symbol area)
            val box1Y = canvasHeight * 0.22f
            if (currentBeamY >= box1Y - 80f) {
                drawRoundRect(
                    color = ElectricCyan.copy(alpha = 0.6f * glowAlpha),
                    topLeft = Offset(canvasWidth * 0.12f, box1Y),
                    size = Size(canvasWidth * 0.28f, 38.dp.toPx()),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx(), 8.dp.toPx()),
                    style = Stroke(width = 2.dp.toPx())
                )
            }

            // Box 2 (First medicine line)
            val box2Y = canvasHeight * 0.42f
            if (currentBeamY >= box2Y - 80f) {
                drawRoundRect(
                    color = ElectricCyan.copy(alpha = 0.7f * glowAlpha),
                    topLeft = Offset(canvasWidth * 0.12f, box2Y),
                    size = Size(canvasWidth * 0.76f, 44.dp.toPx()),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx(), 8.dp.toPx()),
                    style = Stroke(width = 2.dp.toPx())
                )
            }

            // Box 3 (Second medicine line)
            val box3Y = canvasHeight * 0.58f
            if (currentBeamY >= box3Y - 80f) {
                drawRoundRect(
                    color = ElectricBlue.copy(alpha = 0.7f * glowAlpha),
                    topLeft = Offset(canvasWidth * 0.12f, box3Y),
                    size = Size(canvasWidth * 0.76f, 44.dp.toPx()),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx(), 8.dp.toPx()),
                    style = Stroke(width = 2.dp.toPx())
                )
            }

            // Box 4 (Third medicine line)
            val box4Y = canvasHeight * 0.72f
            if (currentBeamY >= box4Y - 80f) {
                drawRoundRect(
                    color = ElectricCyan.copy(alpha = 0.8f * glowAlpha),
                    topLeft = Offset(canvasWidth * 0.12f, box4Y),
                    size = Size(canvasWidth * 0.76f, 44.dp.toPx()),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(8.dp.toPx(), 8.dp.toPx()),
                    style = Stroke(width = 2.dp.toPx())
                )
            }

            // 3. Laser glow trail (gradient above/below beam)
            val trailHeight = 60.dp.toPx()
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        ElectricCyan.copy(alpha = 0.18f),
                        ElectricCyan.copy(alpha = 0.55f)
                    ),
                    startY = currentBeamY - trailHeight,
                    endY = currentBeamY
                ),
                topLeft = Offset(0f, currentBeamY - trailHeight),
                size = Size(canvasWidth, trailHeight)
            )

            // 4. Bright laser beam line
            drawLine(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.Transparent,
                        ElectricCyan,
                        Color.White,
                        ElectricCyan,
                        Color.Transparent
                    )
                ),
                start = Offset(0f, currentBeamY),
                end = Offset(canvasWidth, currentBeamY),
                strokeWidth = 3.dp.toPx()
            )
        }

        // Overlay status card at bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(MidnightNavy.copy(alpha = 0.92f))
                .border(1.dp, ElectricCyan.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(36.dp),
                    color = ElectricCyan,
                    trackColor = ElectricBlue.copy(alpha = 0.2f),
                    strokeWidth = 3.dp
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${(progress * 100).toInt()}% - ANALYZING HANDWRITING",
                    color = ElectricCyan,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = statusMessage,
                    color = TextPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
