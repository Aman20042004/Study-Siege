package com.example.studysiege.ui.Calendar.CalendarComponents



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import com.example.studysiege.navigation.model.DayUI
import com.example.studysiege.ui.Calendar.FocusType


@Composable
fun DayItemUI(day: DayUI, selected: DayUI, onClick: (DayUI) -> Unit) {

    val isSelected = day.timeInMillis == selected.timeInMillis

    val color = when (day.type) {
        FocusType.HIGH -> Color(0xFF00FF88)
        FocusType.AVERAGE -> Color(0xFFFFC107)
        FocusType.LOW -> Color(0xFFFF5252)
        FocusType.NONE -> Color.Gray
    }

    Box(
        modifier = Modifier
            .padding(6.dp)
            .size(44.dp)
            .graphicsLayer {
                scaleX = if (isSelected) 1.1f else 1f
                scaleY = if (isSelected) 1.1f else 1f
            }
            .clip(CircleShape)
            .background(
                if (isSelected) Color(0x2200FFFF) else Color.Transparent
            )
            .border(
                if (isSelected) 2.dp else 0.dp,
                Color.Cyan,
                CircleShape
            )
            .clickable { onClick(day) },
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                day.date.toString(),
                color = if (day.isCurrentMonth) Color.White else Color.Gray.copy(0.3f),
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )

            Spacer(Modifier.height(3.dp))

            Box(
                Modifier
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(color.copy(alpha = if (day.isCurrentMonth) 1f else 0.3f))
            )
        }
    }
}