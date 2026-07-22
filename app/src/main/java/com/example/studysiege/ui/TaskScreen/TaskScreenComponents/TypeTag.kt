package com.example.studysiege.ui.TaskScreen.TaskScreenComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TypeTag(type: String) {

    val color = when (type) {
        "Relevant" -> Color(0xFF00FF88)
        "Gap" -> Color(0xFFFF5252)
        "Not Core Task" -> Color(0xFF5F9CFF)
        else -> Color(0xFFFFC107)
    }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color.copy(alpha = 0.15f))
            . border(
                1.dp,
                color.copy(alpha = 0.5f),
                RoundedCornerShape(50)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 5.dp
            )
    ) {

        Text(
            text = type,
            color = color,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}