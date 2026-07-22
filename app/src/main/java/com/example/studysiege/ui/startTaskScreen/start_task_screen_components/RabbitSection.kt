package com.example.studysiege.ui.startTaskScreen.start_task_screen_components



// Core Compose
import androidx.compose.runtime.Composable
// Layout
import androidx.compose.foundation.layout.*
// UI
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Graphics
import androidx.compose.ui.graphics.Color
// Shapes
import androidx.compose.foundation.shape.RoundedCornerShape
// Background, Border, Clip
import androidx.compose.foundation.background
import androidx.compose.ui.draw.clip
// Text & Material
import androidx.compose.material3.Text
// Image
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.studysiege.R

@Preview
@Composable
fun RabbitSection() {

    Row(verticalAlignment = Alignment.CenterVertically) {

        Image(
            painter = painterResource(id = R.drawable.analytics_rabbit),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        Spacer(Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF11131A))
                .padding(12.dp)
        ) {
            Column {
                Text(
                    "What’s the mission today, Warrior?",
                    color = Color(0xFF8A5CFF)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "Every focused minute is a step closer to victory.",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }
    }
}