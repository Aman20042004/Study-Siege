package com.example.studysiege.room.dao

import androidx.room.*
import com.example.studysiege.room.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("SELECT * FROM tasks WHERE status = 'Running'")
    fun getRunningTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE status = 'Paused'")
    fun getPausedTasks(): Flow<List<TaskEntity>>

    @Query("""
    SELECT * FROM tasks
    WHERE status = 'Completed'
    ORDER BY completedAt DESC
""")
    fun getCompletedTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE status = 'Running' LIMIT 1")
    fun getCurrentRunningTask(): Flow<TaskEntity?>

    @Query("SELECT * FROM tasks WHERE status = 'Running' LIMIT 1")
    suspend fun getCurrentRunningTaskOnce(): TaskEntity?

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskById(taskId: Int): Flow<TaskEntity?>

    @Query("SELECT * FROM tasks WHERE id = :taskId LIMIT 1")
    suspend fun getTaskByIdOnce(taskId: Int): TaskEntity?

    @Query("""
SELECT * FROM tasks
WHERE status = 'Completed'
ORDER BY completedAt ASC
""")
    suspend fun getAllCompletedTasks(): List<TaskEntity>

    @Query("""SELECT * FROM tasks
    WHERE createdAt BETWEEN :startOfDay AND :endOfDay
""")
    fun getTasksForDate(
        startOfDay: Long,
        endOfDay: Long
    ): Flow<List<TaskEntity>>

    @Insert
    suspend fun insertTask(task: TaskEntity): Long



// cloud related queries

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<TaskEntity>)

// queries for sync

    @Query("SELECT * FROM tasks WHERE isSynced = 0")
    suspend fun getUnsyncedTasks(): List<TaskEntity>

    @Query("UPDATE tasks SET isSynced = 1 WHERE id IN (:taskIds)")
    suspend fun markTasksAsSynced(taskIds: List<Int>)

}