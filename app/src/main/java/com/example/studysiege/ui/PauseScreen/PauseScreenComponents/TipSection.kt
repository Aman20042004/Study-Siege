package com.example.studysiege.ui.PauseScreen.PauseScreenComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TipSection() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFF0B0F1A))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            Icons.Default.Lightbulb,
            contentDescription = null,
            tint = Color(0xFF8A5CFF)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            "Tip from Rabbit: A fresh mind fights better! Take a break, not a defeat.",
            color = Color.Gray,
            fontSize = 12.sp
        )

        Spacer(Modifier.weight(1f))

        Icon(
            Icons.Default.Favorite,
            contentDescription = null,
            tint = Color(0xFF8A5CFF)
        )
    }
}