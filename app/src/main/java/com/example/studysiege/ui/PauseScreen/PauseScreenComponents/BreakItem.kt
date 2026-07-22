package com.example.studysiege.ui.PauseScreen.PauseScreenComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BreakItem(
    icon: ImageVector,
    title: String,
    time: String,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .width(80.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }   // 👈 Ye naya
            .border(
                1.dp,
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF8A5CFF),
                        Color(0xFF5F9CFF)
                    )
                ),
                RoundedCornerShape(16.dp)
            )
            .padding(vertical = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(icon, null, tint = Color.Gray)

        Spacer(Modifier.height(6.dp))

        Text(title, color = Color.White, fontSize = 12.sp)

        Text(time, color = Color.Gray, fontSize = 11.sp)
    }
}