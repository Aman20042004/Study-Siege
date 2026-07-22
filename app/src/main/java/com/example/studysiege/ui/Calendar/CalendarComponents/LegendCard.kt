package com.example.studysiege.ui.Calendar.CalendarComponents


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip

@Composable
fun LegendCard() {

    Row(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF111827))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        LegendItem(Color(0xFF00FF88), "High", "80%+")
        LegendItem(Color(0xFFFFC107), "Avg", "50–80%")
        LegendItem(Color(0xFFFF5252), "Low", "<50%")
        LegendItem(Color.Gray, "None", "")
    }
}

@Composable
fun LegendItem(color: Color, title: String, sub: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
            Modifier
                .size(7.dp)
                .clip(CircleShape)
                .background(color)
        )

        Spacer(Modifier.height(3.dp))

        Text(title, color = Color.White, fontSize = 10.sp)

        if (sub.isNotEmpty())
            Text(sub, color = Color.Gray, fontSize = 9.sp)
    }
}