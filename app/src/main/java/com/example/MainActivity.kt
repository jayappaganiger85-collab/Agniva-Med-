package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.screens.DashboardScreen
import com.example.ui.screens.HistoryScreen
import com.example.ui.screens.MedicineInfoScreen
import com.example.ui.screens.ResultScreen
import com.example.ui.screens.ScannerScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.theme.AgnivaMedTheme
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.ElectricCyan
import com.example.ui.theme.MidnightBorder
import com.example.ui.theme.MidnightCard
import com.example.ui.theme.MidnightCardElevated
import com.example.ui.theme.MidnightNavy
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.PrescriptionViewModel
import com.example.ui.viewmodel.Screen

class MainActivity : ComponentActivity() {

    private val viewModel: PrescriptionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val isDarkMode by viewModel.isDarkMode.collectAsState()
            val currentScreen by viewModel.currentScreen.collectAsState()

            AgnivaMedTheme(darkTheme = isDarkMode) {
                // Back button handling
                BackHandler(enabled = currentScreen != Screen.DASHBOARD && currentScreen != Screen.SPLASH) {
                    viewModel.navigateBack()
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MidnightNavy)
                ) {
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding(),
                        containerColor = MidnightNavy,
                        bottomBar = {
                            if (currentScreen != Screen.SPLASH) {
                                AgnivaBottomNavBar(
                                    currentScreen = currentScreen,
                                    onNavigate = { viewModel.navigateTo(it) }
                                )
                            }
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            AnimatedContent(
                                targetState = currentScreen,
                                transitionSpec = {
                                    fadeIn(animationSpec = tween(250)) togetherWith
                                            fadeOut(animationSpec = tween(250))
                                },
                                label = "screen_transition"
                            ) { targetScreen ->
                                when (targetScreen) {
                                    Screen.SPLASH -> SplashScreen(
                                        onContinue = { viewModel.navigateTo(Screen.DASHBOARD) }
                                    )
                                    Screen.DASHBOARD -> DashboardScreen(viewModel = viewModel)
                                    Screen.SCANNER -> ScannerScreen(viewModel = viewModel)
                                    Screen.RESULT -> ResultScreen(viewModel = viewModel)
                                    Screen.HISTORY -> HistoryScreen(viewModel = viewModel)
                                    Screen.MEDICINE_INFO -> MedicineInfoScreen(viewModel = viewModel)
                                    Screen.SETTINGS -> SettingsScreen(viewModel = viewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AgnivaBottomNavBar(
    currentScreen: Screen,
    onNavigate: (Screen) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(26.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MidnightCardElevated.copy(alpha = 0.98f),
                            MidnightCard.copy(alpha = 0.96f)
                        )
                    )
                )
                .border(
                    1.dp,
                    Brush.horizontalGradient(
                        colors = listOf(
                            MidnightBorder,
                            ElectricCyan.copy(alpha = 0.4f),
                            ElectricBlue.copy(alpha = 0.4f),
                            MidnightBorder
                        )
                    ),
                    RoundedCornerShape(26.dp)
                )
                .padding(vertical = 8.dp, horizontal = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NavItem(
                    label = "Home",
                    icon = Icons.Default.Dashboard,
                    isSelected = currentScreen == Screen.DASHBOARD,
                    testTag = "nav_home",
                    onClick = { onNavigate(Screen.DASHBOARD) }
                )
                NavItem(
                    label = "Scan",
                    icon = Icons.Default.DocumentScanner,
                    isSelected = currentScreen == Screen.SCANNER,
                    isProminent = true,
                    testTag = "nav_scanner",
                    onClick = { onNavigate(Screen.SCANNER) }
                )
                NavItem(
                    label = "History",
                    icon = Icons.Default.History,
                    isSelected = currentScreen == Screen.HISTORY,
                    testTag = "nav_history",
                    onClick = { onNavigate(Screen.HISTORY) }
                )
                NavItem(
                    label = "Drugs",
                    icon = Icons.Default.Medication,
                    isSelected = currentScreen == Screen.MEDICINE_INFO,
                    testTag = "nav_drugs",
                    onClick = { onNavigate(Screen.MEDICINE_INFO) }
                )
                NavItem(
                    label = "Settings",
                    icon = Icons.Default.Settings,
                    isSelected = currentScreen == Screen.SETTINGS,
                    testTag = "nav_settings",
                    onClick = { onNavigate(Screen.SETTINGS) }
                )
            }
        }
    }
}

@Composable
private fun NavItem(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    isProminent: Boolean = false,
    testTag: String,
    onClick: () -> Unit
) {
    val bgAnim by animateColorAsState(
        targetValue = when {
            isSelected && isProminent -> ElectricBlue
            isSelected -> ElectricBlue.copy(alpha = 0.25f)
            else -> Color.Transparent
        },
        label = "nav_bg"
    )

    val contentColor by animateColorAsState(
        targetValue = when {
            isSelected && isProminent -> Color.White
            isSelected -> ElectricCyan
            else -> TextMuted
        },
        label = "nav_content"
    )

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bgAnim)
            .clickable { onClick() }
            .padding(horizontal = if (isSelected) 14.dp else 10.dp, vertical = 6.dp)
            .testTag(testTag),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = contentColor,
                modifier = Modifier.size(if (isProminent) 24.dp else 20.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                fontSize = 10.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = contentColor
            )
        }
    }
}
