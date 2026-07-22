package com.example.studysiege.ui.PauseScreen.PauseScreenComponents

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
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studysiege.room.entity.TaskEntity


@Composable
fun BreakStatsCard(
    totalBreakTime: Long,
    totalBreaks: Int,
    modifier: Modifier = Modifier
)  {

    val hours = totalBreakTime / 3600
    val minutes = (totalBreakTime % 3600) / 60

    val formattedTime =
        if (hours > 0)
            "${hours}h ${minutes}m"
        else
            "${minutes}m"

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF0B0F1A))
            .padding(16.dp)
    ) {

        Text(
            "Your Break Stats",
            color = Color(0xFF8A5CFF),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            StatItem(
                value = if (hours > 0) "${hours}h ${minutes}m" else "${minutes}m",
                label = "Total Break Time Today",
                icon = Icons.Default.AccessTime,
                color = Color(0xFFFFA726)
            )

            StatItem(
                value = totalBreaks.toString(),
                label = "Breaks Today",
                icon = Icons.Default.CalendarToday,
                color = Color(0xFF4CAF50)
            )

            // Abhi hardcoded rehne do
            StatItem(
                value = "12%",
                label = "Break Time vs Focus",
                icon = Icons.Default.ShowChart,
                color = Color(0xFF42A5F5)
            )
        }
    }
}
