package com.example.studysiege.ui.Analytics.AnalyticScreenComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



@Composable
fun StatsRow(
    relevantSeconds: Long,
    gapSeconds: Long,
    notCoreSeconds: Long
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
           // .padding(top = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF0B0F1A))
            .border(
                1.dp,
                Brush.horizontalGradient(
                    listOf(Color(0xFF8A5CFF), Color(0xFF5F9CFF))
                ),
                RoundedCornerShape(10.dp)
            )
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        StatItem(
            icon = Icons.Default.HourglassBottom ,
            title = "Relevant",
            value = formatTime(relevantSeconds),
            color = Color(0xFF00FF88)
        )

        StatItem(
            icon = Icons.Default.Delete,
            title = "Gap",
            value = formatTime(gapSeconds),
            color = Color(0xFFFF5252)
        )

        StatItem(
            icon = Icons.Default.Star,
            title = "Not Core",
            value = formatTime(notCoreSeconds),
            color = Color(0xFFFFC107)
        )

     /*   StatItem(
            icon = Icons.Default.Flag ,
            title = "Daily Goal",
            value = "8h 00m",
            color = Color(0xFF5F9CFF)
        )

      */
    }
}

fun formatTime(seconds: Long): String {

    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60

    return "${hours}h ${minutes}m"
}