package com.example.studysiege.ui.Calendar.CalendarComponents


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList

@Composable
fun HeaderCalendar() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = {}) {
            Icon(Icons.Default.ArrowBack, null, tint = Color.White)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Smart Calendar", color = Color.White, fontWeight = FontWeight.Bold)
            Text(
                "Tap on any date to view your Siege Report",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }

        IconButton(onClick = {}) {
            Icon(Icons.Default.FilterList, null, tint = Color.White)
        }
    }
}