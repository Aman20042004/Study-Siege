package com.example.studysiege.room.database

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    @Volatile
    private var INSTANCE: StudySiegeDatabase? = null

    fun getDatabase(context: Context): StudySiegeDatabase {

        return INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                StudySiegeDatabase::class.java,
                "study_siege_db"
            ).build()

            INSTANCE = instance
            instance
        }
    }
}