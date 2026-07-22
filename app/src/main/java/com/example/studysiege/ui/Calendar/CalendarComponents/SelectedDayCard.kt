package com.example.studysiege.ui.Calendar.CalendarComponents


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun SelectedDayCard(
    day: Int,
    onViewReport: () -> Unit
){

    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFF111827))
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(Icons.Default.DateRange, null, tint = Color(0xFF8A5CFF))

            Spacer(Modifier.width(12.dp))

            Column {
                Text("$day May 2025", color = Color.White)
                Text("High Focus Day", color = Color(0xFF00FF88))
                Text(
                    "Great job, Warrior!",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        Box(
            Modifier
                .clip(RoundedCornerShape(12.dp))
                .clickable {
                    onViewReport()
                }
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFF8A5CFF), Color(0xFF5F9CFF))
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text("View Report", color = Color.White)
        }
    }
}