package com.example.studysiege.ui.startTaskScreen


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
fun TopBar() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(32.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF11131A))
                .padding(6.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Start New Task", color = Color.White, fontSize = 18.sp)
            Text(
                "Plan your attack. Stay focused. Win.",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF1A1F2E))
                .padding(horizontal = 10.dp, vertical = 6.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🔥", fontSize = 14.sp)
                Spacer(Modifier.width(4.dp))
                Text("12", color = Color.White)
            }
        }
    }
}