package com.example.studysiege.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studysiege.room.dao.TaskDao
import com.example.studysiege.room.entity.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StudySiegeDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}