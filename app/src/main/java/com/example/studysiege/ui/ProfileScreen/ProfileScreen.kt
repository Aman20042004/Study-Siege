package com.example.studysiege.ui.ProfileScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.studysiege.data.datastore.CloudPreferences
import com.example.studysiege.network.mapper.CloudRepository
import com.example.studysiege.repository.TaskRepository
import com.example.studysiege.room.database.DatabaseProvider
import com.example.studysiege.ui.ProfileScreen.Cloud_Login.CloudAccountBottomSheet
import com.example.studysiege.ui.ProfileScreen.ProfileScreenComponents.AchievementsSection
import com.example.studysiege.ui.ProfileScreen.ProfileScreenComponents.HeaderProfile
import com.example.studysiege.ui.ProfileScreen.ProfileScreenComponents.ProfileTopCard
import com.example.studysiege.ui.ProfileScreen.ProfileScreenComponents.RecentActivity
import com.example.studysiege.ui.ProfileScreen.ProfileScreenComponents.StatsRow
import com.example.studysiege.viewModel.CloudViewModel
import com.example.studysiege.viewModel.TaskViewModel
import androidx.compose.material3.Text


//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {

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


    val cloudViewModel = remember {
        CloudViewModel(
            repository = TaskRepository(
                taskDao,
                cloudRepository
            ),
            preferences = CloudPreferences(context)
        )
    }

    val running =
        taskViewModel.runningTasks.collectAsState()

    val paused =
        taskViewModel.pausedTasks.collectAsState()

    val completed =
        taskViewModel.completedTasks.collectAsState()

    val allTasks =
        running.value +
                paused.value +
                completed.value

    var showCloudSheet by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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

        HeaderProfile(
            onSettingsClick = {
                showCloudSheet = true
            }
        )

        Spacer(Modifier.height(16.dp))

        ProfileTopCard()

        Spacer(Modifier.height(20.dp))

        StatsRow()

        Spacer(Modifier.height(20.dp))

        RecentActivity(
            tasks = allTasks
        )
        Spacer(Modifier.height(10.dp))
        AchievementsSection(tasks=allTasks)

        Spacer(Modifier.height(10.dp))
      //  BottomNavBar()
      //  BottomNavBar(navController )

    }

    if (showCloudSheet) {

        CloudAccountBottomSheet(

            isLoggedIn = cloudViewModel.isLoggedIn,

            loading = cloudViewModel.loading,

            message = cloudViewModel.message,

            studyId = cloudViewModel.studyId,

            onDismiss = {
                showCloudSheet = false
            },

            onLogin = { id, password ->
                cloudViewModel.login(id, password)
            },

            onRegister = { id, password ->
                cloudViewModel.register(id, password)
            },

            onSyncNow = {
                cloudViewModel.syncNow()
            },

            onLogout = {
                cloudViewModel.logout()
            }
        )
    }
}