package com.example.studysiege.ui.ProfileScreen.ProfileScreenComponents



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studysiege.R
import com.example.studysiegeui.ui.theme.Blue
import com.example.studysiegeui.ui.theme.Purple


@Composable
fun ProfileTopCard() {

    Row(verticalAlignment = Alignment.CenterVertically) {

        ProfileRabbitImage() //

        Spacer(Modifier.width(16.dp))

        Column {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Study Warrior",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Spacer(Modifier.width(6.dp))
                Icon(Icons.Default.Edit, null, tint = Purple, modifier = Modifier.size(16.dp))
            }

            Text(
                "Discipline. Focus. Victory.",
                color = Color.Gray,
                fontSize = 12.sp
            )

            Spacer(Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFF1A1F2E))
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text("Siege Level 7", color = Purple, fontSize = 12.sp)
            }

            Spacer(Modifier.height(8.dp))

            Text("2,450 / 3,000 XP", color = Color.Gray, fontSize = 12.sp)

            Spacer(Modifier.height(4.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF1A1F2E))
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight()
                        .background(
                            Brush.horizontalGradient(listOf(Purple, Blue))
                        )
                )
            }
        }
    }
}