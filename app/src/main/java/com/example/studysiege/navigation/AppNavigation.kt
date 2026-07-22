package com.example.studysiege.navigation


import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink

import com.example.studysiege.ui.Analytics.AnalyticsScreen
import com.example.studysiege.ui.Calendar.CalendarScreen
import com.example.studysiege.ui.PauseScreen.PauseScreen
import com.example.studysiege.ui.ProfileScreen.ProfileScreen
import com.example.studysiege.ui.WelcomeScreen
import com.example.studysiege.ui.startTaskScreen.StartTaskScreen
import com.example.studysiegeui.ui.model.Screen
import com.example.studysiegeui.ui.screens.TaskScreen.TaskScreen


@Composable
fun AppNavigation(
    isFirstLaunch: Boolean
) {

    val navController = rememberNavController()

    val context = LocalContext.current


    val startDestination =
        if (isFirstLaunch)
            Screen.Welcome.route
        else
            Screen.Home.route


    val currentRoute =
        navController.currentBackStackEntryAsState().value
            ?.destination?.route

    val showBottomBar =
        currentRoute == Screen.Home.route ||
                currentRoute?.startsWith("analytics") == true ||
                currentRoute == Screen.Calendar.route ||
                currentRoute == Screen.Profile.route ||
                currentRoute == Screen.StartTask.route ||
                currentRoute?.startsWith("pause") == true

    Scaffold(

        bottomBar = {

            if (showBottomBar) {

                BottomNavBar(navController)
            }
        }

    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = startDestination,
        ){

            composable(Screen.Welcome.route) {
                WelcomeScreen(navController)
            }

            composable(Screen.Home.route) {
                TaskScreen(navController)
            }

            composable(
                route = Screen.Analytics.route
            ) { backStackEntry ->

                val date =
                    backStackEntry.arguments
                        ?.getString("date")
                        ?.toLongOrNull()
                        ?: System.currentTimeMillis()

                AnalyticsScreen(
                    navController = navController,
                    selectedDate = date
                )
            }

            composable(Screen.Calendar.route) {
                CalendarScreen(navController)
            }




            composable(Screen.Profile.route) {
                ProfileScreen(navController)
            }



            composable(Screen.StartTask.route) {
                StartTaskScreen(navController)
            }



            composable(
                route = Screen.Pause.route,

                deepLinks = listOf(
                    navDeepLink {
                        uriPattern = "studysiege://pause/{taskId}"
                    }
                )

            ) { backStackEntry ->

                val taskId =
                    backStackEntry.arguments
                        ?.getString("taskId")
                        ?.toIntOrNull()
                        ?: 0

                PauseScreen(
                    navController = navController,
                    taskId = taskId
                )
            }
        }
    }
}
