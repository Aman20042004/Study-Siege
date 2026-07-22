package com.example.studysiege.ui.PauseScreen.PauseScreenComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StopEndButton(onClick:()-> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                onClick()
            }
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0B0F1A))
            .border(
                1.dp,
                Color.Red,
                RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Icon(Icons.Default.Delete, null, tint = Color.Red)

            Spacer(Modifier.width(6.dp))

            Column {
                Text("Stop & End Task", color = Color.Red, fontWeight = FontWeight.Bold)
                Text("End this task and review", color = Color.Gray, fontSize = 11.sp)
            }
        }
    }
}