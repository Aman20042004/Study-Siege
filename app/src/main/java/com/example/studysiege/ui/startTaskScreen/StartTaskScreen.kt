package com.example.studysiege.ui.startTaskScreen


// Core Compose
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// Layout
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState

// UI
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp

// Graphics
import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import com.example.studysiege.network.mapper.CloudRepository
import com.example.studysiege.notification.NotificationHelper
import com.example.studysiege.repository.TaskRepository
import com.example.studysiege.room.database.DatabaseProvider

import com.example.studysiege.ui.startTaskScreen.start_task_screen_components.ModeSection
import com.example.studysiege.ui.startTaskScreen.start_task_screen_components.MotivationCard
import com.example.studysiege.ui.startTaskScreen.start_task_screen_components.RabbitSection
import com.example.studysiege.ui.startTaskScreen.start_task_screen_components.StartButton
import com.example.studysiege.ui.startTaskScreen.start_task_screen_components.TaskNameField
import com.example.studysiege.ui.startTaskScreen.start_task_screen_components.TaskTypeSection
import com.example.studysiege.ui.startTaskScreen.start_task_screen_components.WheelPicker.CustomDurationBottomSheet
import com.example.studysiege.viewModel.TaskViewModel
import com.example.studysiegeui.ui.model.Screen
import kotlinx.coroutines.launch


//@Preview(showSystemUi = true)
@Composable
fun StartTaskScreen(navController: NavController) {

//fun StartTaskScreen() {
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
    var taskName by remember { mutableStateOf("")}
    var showEmptyTaskError by remember {
        mutableStateOf(false)
    }
    var selectedType by remember { mutableStateOf("Relevant") }
    var selectedMode by remember { mutableStateOf("Stopwatch") }
    var selectedDuration by remember { mutableStateOf("2h") }
    var showCustomDuration by remember {
        mutableStateOf(false)
    }

    var customDurationSeconds by remember {
        mutableLongStateOf(0L)
    }

    var customDurationText by remember {
        mutableStateOf("Custom")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF05070D)).padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(Modifier.height(15.dp))
            
            RabbitSection()

            Spacer(Modifier.height(15.dp))

            TopBar()

            Spacer(Modifier.height(15.dp))

            TaskTypeSection(
                selected = selectedType,
                onSelect = { selectedType = it }
            )
            Spacer(Modifier.height(15.dp))

            TaskNameField(

                taskName = taskName,

                onValueChange = {

                    taskName = it

                    showEmptyTaskError = false
                }
            )
            if (showEmptyTaskError) {

                Spacer(Modifier.height(4.dp))

                Text(

                    text = "Please enter task name",

                    color = Color.Red,

                    fontSize = 12.sp
                )
            }

            Spacer(Modifier.height(15.dp))

            ModeSection(
                selectedMode = selectedMode,

                onModeChange = {
                    selectedMode = it
                },

                selectedDuration = selectedDuration,

                customDurationText = customDurationText,

                onDurationChange = {

                    if (it == "custom") {

                        showCustomDuration = true

                    } else {

                        selectedDuration = it
                    }
                }


            )

            Spacer(Modifier.height(15.dp))

            MotivationCard()

            Spacer(Modifier.height(15.dp))

            StartButton {

                if (taskName.isBlank()) {

                    showEmptyTaskError = true

                    return@StartButton
                }

                scope.launch {

                    val durationInSeconds = when (selectedDuration) {

                        "30m" -> 30 * 60L

                        "1h" -> 60 * 60L

                        "2h" -> 2 * 60 * 60L

                        "3h" -> 3 * 60 * 60L

                        "custom" -> customDurationSeconds

                        else -> 0L
                    }

                    val taskId =
                        taskViewModel.createTask(
                            title = taskName.trim(),
                            type = selectedType,
                            mode = selectedMode,
                            targetDuration = durationInSeconds
                        )

                    NotificationHelper.startTimerService(
                        context = context,
                        taskId=  taskId,
                        title = taskName.trim(),
                        type = selectedType,
                        mode = selectedMode,
                        elapsedSeconds = 0L,
                        lastResumeTime = System.currentTimeMillis(),
                        targetDuration = durationInSeconds
                    )

                    navController.navigate(
                        Screen.Pause.createRoute(taskId)
                    )
                }
            }

            if (showCustomDuration) {

                CustomDurationBottomSheet(

                    onDismiss = {

                        showCustomDuration = false
                    },

                    onConfirm = { seconds ->

                        customDurationSeconds = seconds

                        selectedDuration = "custom"

                        val h = seconds / 3600

                        val m = (seconds % 3600) / 60

                        val s = seconds % 60

                        customDurationText =

                            when {

                                h > 0 && m > 0 ->
                                    "${h}h ${m}m"

                                h > 0 ->
                                    "${h}h"

                                m > 0 ->
                                    "${m}m"

                                else ->
                                    "${s}s"
                            }

                        showCustomDuration = false
                    }
                )
            }

            Spacer(Modifier.height(15.dp))
          //  BottomNavBar()
            //BottomNavBar(navController)
        }

    }
}