package com.example.studysiegeui.ui.screens.TaskScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studysiege.notification.NotificationHelper
import com.example.studysiege.room.database.DatabaseProvider
import com.example.studysiege.viewModel.TaskViewModel
import com.example.studysiege.ui.TaskScreen.TaskScreenComponents.CompletedTaskCard
import com.example.studysiege.ui.TaskScreen.TaskScreenComponents.HeaderTasks
import com.example.studysiege.ui.TaskScreen.TaskScreenComponents.PausedTaskCard
import com.example.studysiege.ui.TaskScreen.TaskScreenComponents.RunningTaskCard
import com.example.studysiege.ui.TaskScreen.TaskScreenComponents.TaskTabs
import com.example.studysiegeui.ui.model.Screen
import kotlinx.coroutines.delay
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.studysiege.network.mapper.CloudRepository
import com.example.studysiege.repository.TaskRepository
import com.example.studysiege.ui.TaskScreen.getHistoryHeader


//@Preview(showSystemUi = true)
@Composable
//fun TasksScreen(){
fun TaskScreen(navController: NavController) {
    var selectedTab by remember {
        mutableStateOf("Running")
    }

    val context = LocalContext.current

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
    var tick by remember {
        mutableLongStateOf(0)
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            tick++
        }
    }
    val runningTasks =
        taskViewModel.runningTasks.collectAsState()

    val pausedTasks =
        taskViewModel.pausedTasks.collectAsState()

    val completedTasks =
        taskViewModel.completedTasks.collectAsState()

    Column(
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
            .padding(16.dp)
    ) {

        HeaderTasks()

        Spacer(Modifier.height(16.dp))

        TaskTabs(
            selectedTab = selectedTab,
            onTabSelected = {
                selectedTab = it
            }
        )

        Spacer(Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {

            when (selectedTab) {

                "All" -> {

                    item {

                        Text(
                            "Running Tasks",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(10.dp))
                    }

                    items(runningTasks.value) { task ->

                        val displaySeconds = run {
                            tick
                            task.elapsedSeconds +
                                    (System.currentTimeMillis() - task.lastResumeTime) / 1000
                        }

                        RunningTaskCard(
                            title = task.title,
                            type = task.type,
                            mode = task.mode,
                            targetDuration = task.targetDuration,
                            elapsedTime = displaySeconds.toString(),
                            onClick = {
                                navController.navigate(
                                    Screen.Pause.createRoute(task.id)
                                )
                            },
                            onDelete = {
                                NotificationHelper.stopTimerService(context)
                                taskViewModel.deleteTask(task)
                            }
                        )
                    }

                    item {
                        Spacer(Modifier.height(20.dp))

                        Text(
                            "Paused Tasks",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(10.dp))
                    }

                    items(pausedTasks.value) { task ->

                        PausedTaskCard(
                            title = task.title,
                            type = task.type,
                            mode = task.mode,
                            targetDuration = task.targetDuration,
                            elapsedTime = task.elapsedSeconds.toString(),
                            onClick = {
                                navController.navigate(
                                    Screen.Pause.createRoute(task.id)
                                )
                            },
                            onDelete = {
                                taskViewModel.deleteTask(task)
                            }
                        )
                    }

                    item {
                        Spacer(Modifier.height(20.dp))

                        Text(
                            "Completed Tasks",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(10.dp))
                    }

                    items(completedTasks.value) { task ->

                        CompletedTaskCard(
                            title = task.title,
                            type = task.type,
                            elapsedTime = task.elapsedSeconds.toString(),
                            onClick = {},
                            onDelete = {
                                taskViewModel.deleteTask(task)
                            }
                        )
                    }
                }

                "Paused" -> {

                    item {

                        Text(
                            "Paused Tasks",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(10.dp))
                    }

                    if (pausedTasks.value.isEmpty()) {

                        item {

                            Text(
                                text = "No Paused Tasks",
                                color = Color.Gray
                            )
                        }

                    } else {

                        items(pausedTasks.value) { task ->

                            PausedTaskCard(

                                title = task.title,

                                type = task.type,

                                mode = task.mode,

                                targetDuration = task.targetDuration,

                                elapsedTime = task.elapsedSeconds.toString(),

                                onClick = {

                                    navController.navigate(
                                        Screen.Pause.createRoute(task.id)
                                    )
                                },

                                onDelete = {

                                    taskViewModel.deleteTask(task)
                                }
                            )
                        }
                    }
                }

                "Completed" -> {

                    val groupedTasks =
                        completedTasks.value.groupBy {
                            getHistoryHeader(it.completedAt)
                        }

                    groupedTasks.forEach { (header, tasks) ->

                        item {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = header,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )

                                Text(
                                    text = "${tasks.size} Tasks",
                                    color = Color.Gray,
                                    fontSize = 12.sp
                                )
                            }

                            Spacer(Modifier.height(10.dp))
                        }

                        items(tasks) { task ->

                            CompletedTaskCard(

                                title = task.title,

                                type = task.type,

                                elapsedTime = task.elapsedSeconds.toString(),

                                onClick = {},

                                onDelete = {

                                    taskViewModel.deleteTask(task)
                                }
                            )
                        }

                        item {

                            Spacer(Modifier.height(20.dp))
                        }
                    }
                }

                "Running" -> {

                    item {

                        Text(
                            "Running Tasks",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(Modifier.height(10.dp))
                    }

                    if (runningTasks.value.isEmpty()) {

                        item {

                            Text(
                                text = "No Running Tasks",
                                color = Color.Gray
                            )
                        }

                    } else {

                        items(runningTasks.value) { task ->

                            val displaySeconds = run {

                                tick

                                task.elapsedSeconds +
                                        (System.currentTimeMillis() - task.lastResumeTime) / 1000
                            }

                            RunningTaskCard(

                                title = task.title,

                                type = task.type,

                                mode = task.mode,

                                targetDuration = task.targetDuration,

                                elapsedTime = displaySeconds.toString(),

                                onClick = {

                                    navController.navigate(
                                        Screen.Pause.createRoute(task.id)
                                    )
                                },

                                onDelete = {

                                    NotificationHelper.stopTimerService(context)

                                    taskViewModel.deleteTask(task)
                                }
                            )
                        }
                    }
                }
            }




            // BottomNavBar(navController )
            // BottomNavBar()
        }
    }
}