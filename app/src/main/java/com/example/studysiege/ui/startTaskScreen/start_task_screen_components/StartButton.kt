package com.example.studysiege.ui.startTaskScreen.start_task_screen_components

// Core Compose
import androidx.compose.runtime.Composable

// Layout
import androidx.compose.foundation.layout.*

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
import androidx.compose.foundation.clickable
import androidx.compose.ui.draw.clip

// Text & Material
import androidx.compose.material3.Text
import androidx.compose.ui.draw.shadow

// Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview


//@Preview
@Composable
fun StartButton(onClick:() -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .clickable{onClick()}
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xFF8A5CFF), Color(0xFF5F9CFF))
                )
            )
            .shadow(
                elevation = 20.dp,
                ambientColor = Color(0xFF8A5CFF).copy(alpha = 0.5f),
                spotColor = Color(0xFF5F9CFF).copy(alpha = 0.5f)
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                "Start Task",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Text(
                "Begin your next attack",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )
        }
    }
}