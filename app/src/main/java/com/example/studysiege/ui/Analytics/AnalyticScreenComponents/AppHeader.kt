package com.example.studysiege.ui.Analytics.AnalyticScreenComponents

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun AppHeader(

    title: String,

    subtitle: String,

    showMenu: Boolean,

    showNotification: Boolean,



    onOverviewClick: () -> Unit = {}

) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
          //  .padding(top = .dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // 🔥 Menu Button
        Box(
            modifier = Modifier
                .size(30.dp).clickable {
                    onOverviewClick()
                }
                .border(
                    1.dp,
                    Brush.horizontalGradient(
                        listOf(Color(0xFF8A5CFF), Color(0xFF5F9CFF))
                    ),
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Menu, null, tint = Color.White)
        }

        Column(
            modifier = Modifier.weight(1f).padding(start = 12.dp)
        ) {
            Text(
                "Good evening, Warrior!",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Stay focused. Win the Siege.",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }

        // 🔥 Notification
        Box(
            modifier = Modifier
                .size(30.dp)
                .border(
                    1.dp,
                    Brush.horizontalGradient(
                        listOf(Color(0xFF8A5CFF), Color(0xFF5F9CFF))
                    ),
                    RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Notifications, null, tint = Color.White)
        }
    }
}
