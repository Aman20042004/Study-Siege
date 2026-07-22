package com.example.studysiege.ui.Calendar.CalendarComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TrophyMessage() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            Icons.Default.EmojiEvents,
            contentDescription = null,
            tint = Color(0xFF8A5CFF),
            modifier = Modifier.size(22.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            "You're in the top ",
            color = Color.Gray,
            fontSize = 12.sp
        )

        Text(
            "24%",
            color = Color(0xFF8A5CFF),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )

        Text(
            " of focused warriors this month!",
            color = Color.Gray,
            fontSize = 12.sp
        )
    }
}