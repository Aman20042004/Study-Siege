package com.example.studysiege.ui.ProfileScreen.ProfileScreenComponents



import androidx.compose.foundation.layout.*

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import com.example.studysiege.R
import androidx.compose.ui.Alignment

import androidx.compose.ui.res.painterResource
import com.example.studysiege.room.entity.TaskEntity


data class Achievement(
    val imageRes: Int,
    val title: String,
    val progressText: String,
    val unlocked: Boolean
)


@Composable
fun AchievementsSection(

    tasks: List<TaskEntity>
) {

    val completedTasks =
        tasks.count {
            it.status == "Completed"
        }

    val relevantTasks =
        tasks.count {
            it.type == "Relevant" &&
                    it.status == "Completed"
        }

    val totalHours =
        tasks.sumOf {
            it.elapsedSeconds
        } / 3600

    val achievements = listOf(

        Achievement(

            imageRes = R.drawable.consistency,

            title = "Streak Keeper",

            progressText = "$completedTasks / 7 Tasks",

            unlocked = completedTasks >= 7
        ),

        Achievement(

            imageRes = R.drawable.test,

            title = "Test Warrior",

            progressText = "$completedTasks / 10 Tasks",

            unlocked = completedTasks >= 10
        ),

        Achievement(

            imageRes = R.drawable.time,

            title = "Time Master",

            progressText = "$totalHours / 50 Hours",

            unlocked = totalHours >= 50
        )
    )

    Column {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Achievements",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "View All",
                color = Color(0xFF8A5CFF),
                fontSize = 15.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            achievements.forEach {

                AchievementItem(
                    achievement = it,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun AchievementItem(
    achievement: Achievement,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(

            painter = painterResource(achievement.imageRes),

            alpha =
                if (achievement.unlocked)
                    1f
                else
                    0.35f,

            contentDescription = null,

            modifier = Modifier.size(85.dp)
        )

        Spacer(Modifier.height(10.dp))

        Text(
            text = achievement.title,
            color =
                if (achievement.unlocked)
                    Color.White
                else
                    Color.Gray,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(Modifier.height(3.dp))

        Text(
            text = achievement.progressText,
            color =
                if (achievement.unlocked)
                    Color(0xFF00FF88)
                else
                    Color.LightGray,
            fontSize = 10.sp
        )

    }
}



