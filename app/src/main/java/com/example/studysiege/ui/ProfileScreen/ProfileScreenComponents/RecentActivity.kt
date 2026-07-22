package com.example.studysiege.ui.ProfileScreen.ProfileScreenComponents



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studysiege.room.entity.TaskEntity

@Composable
fun RecentActivity(
    tasks: List<TaskEntity>
) {

    val recentTasks =
        tasks
            .sortedByDescending { it.createdAt }
            .take(5)

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Recent Activity",
                color = Color.White
            )

            Text(
                "View All",
                color = Color(0xFF8A5CFF)
            )
        }

        Spacer(Modifier.height(4.dp))

        LazyColumn(
            modifier = Modifier.height(172.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {

            items(recentTasks) { task ->

                ActivityItem(
                    title = task.title,
                    type = task.type
                )
            }
        }
    }
}

@Composable
fun ActivityItem(
    title: String,
    type: String
) {

    val color = getTypeColor(type)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFF0B0F1A))
            .padding(10.dp),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column {

            Text(
                title,
                color = Color.White
            )

            Spacer(Modifier.height(2.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(color.copy(alpha = 0.2f))
                    .padding(
                        horizontal = 8.dp,
                        vertical = 2.dp
                    )
            ) {

                Text(
                    text = type,
                    color = color,
                    fontSize = 10.sp
                )
            }
        }

        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = color
        )
    }
}

fun getTypeColor(type: String): Color {

    return when (type) {

        "Relevant" -> Color(0xFF00FF88)

        "Custom" -> Color(0xFFFFC107)

        "Gap" -> Color(0xFFFF5252)

        else -> Color.Gray
    }
}