package com.example.studysiege.ui.ProfileScreen.ProfileScreenComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.studysiegeui.ui.theme.Blue
import com.example.studysiegeui.ui.theme.Green
import com.example.studysiegeui.ui.theme.Orange
import com.example.studysiegeui.ui.theme.Purple

@Composable
fun StatsRow() {

    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF0B0F1A))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        StatItem("24", "Tasks", Icons.Default.Flag, Purple)
        StatItem("18h 45m", "Focus", Icons.Default.AccessTime, Blue)
        StatItem("7", "Streak", Icons.Default.LocalFireDepartment, Green)
        StatItem("12", "Wins", Icons.Default.EmojiEvents, Orange)
    }
}

@Composable
fun StatItem(value: String, label: String, icon: ImageVector, color: Color) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Icon(icon, null, tint = color)

        Spacer(Modifier.height(6.dp))

        Text(value, color = Color.White, fontWeight = FontWeight.Bold)

        Text(label, color = Color.Gray, fontSize = 12.sp)
    }
}