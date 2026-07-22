package com.example.studysiege.ui.startTaskScreen.start_task_screen_components


// Core Compose
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// Layout
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState

// UI
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Graphics
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush

// Shapes
import androidx.compose.foundation.shape.RoundedCornerShape

// Background, Border, Clip
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.ui.draw.clip

// Text & Material
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults

// Icons
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle

// Image
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource

// Font
import androidx.compose.ui.text.font.FontWeight

// Scroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun MotivationCard() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xFF1A1F2E),
                        Color(0xFF10131C)
                    )
                )
            )
            .border(
                1.dp,
                Color(0xFF2A2F3F),
                RoundedCornerShape(18.dp)
            )
            .padding(8.dp)
    ) {

        Column {

            Text(
                "Honesty now = Victory later.",
                color = Color(0xFF8A5CFF),
                fontWeight = FontWeight.SemiBold
            )

            Text(
                "Track truth, change reality.",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}