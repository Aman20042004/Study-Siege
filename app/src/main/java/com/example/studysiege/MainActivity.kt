package com.example.studysiege

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.studysiege.navigation.AppNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT

        WindowInsetsControllerCompat(
            window,
            window.decorView
        ).apply {
            isAppearanceLightStatusBars = false
            isAppearanceLightNavigationBars = false
        }

        setContent {
            AppNavigation(
                isFirstLaunch = isFirstLaunch()
            )
        }
    }


    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    private fun isFirstLaunch(): Boolean {

        val prefs = getSharedPreferences(
            "study_siege",
            MODE_PRIVATE
        )

        val firstLaunch =
            prefs.getBoolean(
                "first_launch",
                true
            )

        if (firstLaunch) {
            prefs.edit()
                .putBoolean(
                    "first_launch",
                    false
                )
                .apply()
        }

        return firstLaunch
    }
}