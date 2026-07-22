package com.example.studysiege.ui.Analytics.AnalyticScreenComponents


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TaskProgressItem(
    title: String,
    time: String,
    progress: Float,
    color: Color
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {

        // 🔥 TITLE + TIME
        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                color = Color.White,
                fontSize = 11.sp
            )

            Text(
                text = time,
                color = Color.Gray,
                fontSize = 10.sp
            )
        }

        Spacer(Modifier.height(6.dp))

        // 🔥 PROGRESS BAR BG
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF1F2937))
        ) {

            // 🔥 PROGRESS FILL
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress.coerceIn(0f, 1f))
                    .height(6.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                color,
                                color.copy(alpha = 0.6f)
                            )
                        )
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskProgressItemPreview() {

    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(16.dp)
    ) {

        TaskProgressItem(
            title = "Mathematics",
            time = "2h 30m",
            progress = 0.9f,
            color = Color(0xFF8A5CFF)
        )

        TaskProgressItem(
            title = "Physics",
            time = "2h 10m",
            progress = 0.75f,
            color = Color(0xFF5F9CFF)
        )
    }
}
@Preview
@Composable
fun TaskProgressItem(){
    TaskProgressItem("aman","time",4.44f,Color(0xFF8A5CFF))
}