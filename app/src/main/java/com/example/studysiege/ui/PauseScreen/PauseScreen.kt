package com.example.studysiege.ui.PauseScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studysiege.R
import com.example.studysiege.network.mapper.CloudRepository
import com.example.studysiege.notification.NotificationHelper
import com.example.studysiege.repository.TaskRepository
import com.example.studysiege.room.database.DatabaseProvider
import androidx.compose.material3.ExperimentalMaterial3Api


import com.example.studysiege.ui.PauseScreen.PauseScreenComponents.BreakItem
import com.example.studysiege.ui.PauseScreen.PauseScreenComponents.BreakStatsCard
import com.example.studysiege.ui.PauseScreen.PauseScreenComponents.ResumeButton
import com.example.studysiege.ui.PauseScreen.PauseScreenComponents.StopEndButton
import com.example.studysiege.ui.PauseScreen.PauseScreenComponents.TipSection
import com.example.studysiege.ui.TaskScreen.TaskScreenComponents.formatDuration
import com.example.studysiege.viewModel.TaskViewModel
import com.example.studysiegeui.ui.model.Screen
import kotlinx.coroutines.delay
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch


@Composable
fun PauseScreen(
    navController: NavController,
    taskId: Int
) {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    val taskDao =
        DatabaseProvider
            .getDatabase(context)
            .taskDao()

    val cloudRepository = remember {
        CloudRepository(taskDao)
    }

    val taskRepository = remember {
        TaskRepository(
            taskDao,
            cloudRepository
        )
    }

    val taskViewModel = remember {
        TaskViewModel(
            taskDao,
            taskRepository
        )
    }

    val task =
        taskViewModel
            .getTaskById(taskId)
            .collectAsState(initial = null)



    LaunchedEffect(task.value) {

        Log.d(
            "StudySiege",
            "PauseScreen taskId=$taskId status=${task.value?.status}"
        )
    }

    var tick by remember {
        mutableLongStateOf(0)
    }

    LaunchedEffect(Unit) {

        while (true) {

            delay(1000)

            tick++
        }
    }


    val isRunning = task.value?.status=="Running"

    val displaySeconds =
        if (task.value?.status == "Running") {

            tick   // <-- sirf state read karne ke liye

            task.value!!.elapsedSeconds +
                    (System.currentTimeMillis() - task.value!!.lastResumeTime) / 1000

        } else {

            task.value?.elapsedSeconds ?: 0
        }

    val hours = displaySeconds / 3600
    val minutes = (displaySeconds % 3600) / 60
    val seconds = displaySeconds % 60

    val formattedTime =
        String.format(
            "%02d:%02d:%02d",
            hours,
            minutes,
            seconds
        )

    var breakStats by remember {
        mutableStateOf(0L to 0)
    }

    LaunchedEffect(Unit) {
        breakStats = taskViewModel.getBreakStats()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0xFF05070D),
                        Color(0xFF0B0F1A),
                        Color(0xFF120F2A)
                    )
                )
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(bottom = 80.dp)
        ) {

            // 🔥 HEADER
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Task Paused", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("Take a break. Recharge.", color = Color.Gray, fontSize = 12.sp)
                }
            }

            Spacer(Modifier.height(8.dp))

            // 🔥 RABBIT + MESSAGE
            Row(verticalAlignment = Alignment.CenterVertically) {

                Image(
                    painter = painterResource(R.drawable.coffee_rabbit),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFF0B0F1A))
                        .border(
                            1.dp,
                            Brush.horizontalGradient(
                                listOf(Color(0xFF8A5CFF), Color(0xFF00D4FF))
                            ),
                            RoundedCornerShape(16.dp)
                        )
                        .padding(6.dp)
                ) {
                    Text(
                        "Good call, Warrior! 🛡️\nEven warriors take breaks.",
                        color = Color.White
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            // 🔥 TIMER CARD
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFF0B0F1A))
                    .padding(16.dp)
            ) {

                Column {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {
                            Text(text=task.value?.mode ?: "", color = Color.Gray, fontSize = 12.sp)
                            Text(
                                task.value?.title ?: "",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color(0xFF00C853).copy(alpha = 0.15f))
                                .border(1.dp, Color(0xFF00C853), RoundedCornerShape(10.dp))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(task.value?.type ?: "", color = Color(0xFF00C853), fontSize = 11.sp)
                        }
                    }

                    Spacer(Modifier.height(14.dp))

                    Text(
                     formattedTime,
                        color = Color.White,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(8.dp))

                    if (task.value?.mode == "Timer") {

                        val target = task.value?.targetDuration ?: 0L

                        val progress =
                            if (target > 0)
                                (displaySeconds.toFloat() / target).coerceIn(0f, 1f)
                            else
                                0f

                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(RoundedCornerShape(50)),
                            color = Color(0xFF8A5CFF),
                            trackColor = Color(0xFF1F2937)
                        )

                        Spacer(Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = "Target: ${formatDuration(target)}",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )

                            val remaining =
                                (target - displaySeconds).coerceAtLeast(0)

                            Text(
                                text = "Remaining: ${formatDuration(remaining)}",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }

                    } else {

                        Spacer(Modifier.height(8.dp))

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text = "Free Focus Session",
                                color = Color(0xFF8A5CFF),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            )

                            Spacer(Modifier.height(4.dp))

                            Text(
                                text = "No Time Limit",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            // 🔥 BREAK OPTIONS
            LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

                items(
                    listOf(
                        Triple(Icons.Default.Coffee, "Short Break", "5 min"),
                        Triple(Icons.Default.SelfImprovement, "Stretch", "10 min"),
                        Triple(Icons.Default.DirectionsWalk, "Walk", "15 min"),
                        Triple(Icons.Default.Spa, "Deep Break", "30 min"),
                        Triple(Icons.Default.Tune, "Custom", "Set Timer")
                    )
                ) { item ->

                    BreakItem(
                        icon = item.first,
                        title = item.second,
                        time = item.third,
                        onClick = {
                            scope.launch {

                                val duration = when (item.second) {
                                    "Short Break" -> 5 * 60L
                                    "Stretch" -> 10 * 60L
                                    "Walk" -> 15 * 60L
                                    "Deep Break" -> 30 * 60L
                                    else -> 0L
                                }

                                val taskId = taskViewModel.startBreak(
                                    title = item.second,
                                    targetDuration = duration
                                )

                                NotificationHelper.startTimerService(
                                    context = context,
                                    taskId = taskId,
                                    title = item.second,
                                    type = "Not Core Task",
                                    mode = "Timer",
                                    elapsedSeconds = 0,
                                    lastResumeTime = System.currentTimeMillis(),
                                    targetDuration = duration
                                )

                                navController.navigate(
                                    Screen.Pause.createRoute(taskId)
                                ) {
                                    popUpTo(Screen.Pause.createRoute(taskId)) {
                                        inclusive = true
                                    }
                                }
                            }
                        }
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            // 🔥 TIP
            TipSection()

            Spacer(Modifier.height(2.dp))

            // 🔥 BREAK STATS
            BreakStatsCard(
                totalBreakTime = breakStats.first,
                totalBreaks = breakStats.second
            )

            Spacer(Modifier.height(4.dp))

            // 🔥 RESUME BUTTON

            ResumeButton(
                isRunning = isRunning,
                onClick = {
                    task.value?.let {
                        if (isRunning) {
                            taskViewModel.pauseTask(it)
                            NotificationHelper.stopTimerService(context)
                        } else {
                            taskViewModel.resumeTaskAndPauseOthers(it)

                            NotificationHelper.startTimerService(
                                context = context,
                                taskId = it.id,
                                title = it.title,
                                type = it.type,
                                mode = it.mode,
                                elapsedSeconds = it.elapsedSeconds,
                                lastResumeTime = System.currentTimeMillis(),
                                targetDuration = it.targetDuration
                            )
                        }
                    }
                }
            )

            Spacer(Modifier.height(5.dp))

            // 🔥 STOP BUTTON
            StopEndButton(

                onClick = {

                    task.value?.let {

                        taskViewModel.completeTask(it)

                        NotificationHelper.stopTimerService(context)
                    }

                    navController.navigate(Screen.Home.route)

                    navController.navigate(Screen.Home.route)
                }
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        ) {
            // BottomNavBar()
            //  BottomNavBar(navController )
        }
    }

}