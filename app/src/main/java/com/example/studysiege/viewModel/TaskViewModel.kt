package com.example.studysiege.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studysiege.navigation.model.FocusConsistencyData
import com.example.studysiege.repository.TaskRepository
import com.example.studysiege.room.dao.TaskDao
import com.example.studysiege.room.entity.TaskEntity
import com.example.studysiegeui.ui.model.DayStatus

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Calendar





class TaskViewModel(

    private val taskDao: TaskDao,

    private val repository: TaskRepository

) : ViewModel() {

    // =========================
    // TASK SCREEN
    // =========================


    val currentRunningTask =
        taskDao
            .getCurrentRunningTask()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                null
            )

    val runningTasks =
        taskDao.getRunningTasks()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    val pausedTasks =
        taskDao.getPausedTasks()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun getTaskById(taskId: Int) =
        taskDao.getTaskById(taskId)

    val completedTasks =
        taskDao.getCompletedTasks()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )


    fun resumeTaskAndPauseOthers(task: TaskEntity) {

        viewModelScope.launch {

            val runningTask = taskDao.getCurrentRunningTaskOnce()

            if (runningTask != null && runningTask.id != task.id) {

                val extraSeconds =
                    (System.currentTimeMillis() - runningTask.lastResumeTime) / 1000

                taskDao.updateTask(
                    runningTask.copy(
                        status = "Paused",
                        elapsedSeconds = runningTask.elapsedSeconds + extraSeconds,
                        lastResumeTime = 0
                    )
                )
            }

            taskDao.updateTask(
                task.copy(
                    status = "Running",
                    lastResumeTime = System.currentTimeMillis()
                )
            )
        }
    }
    // =========================
    // START TASK SCREEN
    // =========================

    suspend fun createTask(
        title: String,
        type: String,
        mode: String,
        targetDuration: Long = 0
    ): Int {

        val runningTask = taskDao.getCurrentRunningTaskOnce()

        if (runningTask != null) {

            val extraSeconds =
                (System.currentTimeMillis() - runningTask.lastResumeTime) / 1000

            taskDao.updateTask(
                runningTask.copy(
                    status = "Paused",
                    elapsedSeconds = runningTask.elapsedSeconds + extraSeconds,
                    lastResumeTime = 0
                )
            )
        }

        val task = TaskEntity(
            title = title,
            type = type,
            status = "Running",
            mode = mode,
            elapsedSeconds = 0,
            lastResumeTime = System.currentTimeMillis(),
            targetDuration = targetDuration
        )

        return taskDao.insertTask(task).toInt()
    }

    suspend fun startBreak(
        title: String,
        targetDuration: Long
    ): Int {

        return createTask(
            title = title,
            type = "Not Core Task",
            mode = "Timer",
            targetDuration = targetDuration
        )
    }

    // =========================
    // PAUSE SCREEN
    // =========================

    fun updateTask(task: TaskEntity) {

        viewModelScope.launch {

            taskDao.updateTask(task)
        }
    }

    fun pauseTask(task: TaskEntity) {

        viewModelScope.launch {

            val extraSeconds =
                (System.currentTimeMillis() - task.lastResumeTime) / 1000

            taskDao.updateTask(
                task.copy(
                    status = "Paused",
                    elapsedSeconds =
                        task.elapsedSeconds + extraSeconds,
                    lastResumeTime = 0
                )
            )
        }
    }

    fun resumeTask(task: TaskEntity) {

        viewModelScope.launch {

            taskDao.updateTask(
                task.copy(
                    status = "Running",
                    lastResumeTime = System.currentTimeMillis()
                )
            )
        }
    }

    fun completeTask(task: TaskEntity) {

        viewModelScope.launch {

            val updatedTask =

                if (task.status == "Running") {

                    val extraSeconds =
                        (System.currentTimeMillis() -
                                task.lastResumeTime) / 1000

                    task.copy(
                        status = "Completed",
                        elapsedSeconds =
                            task.elapsedSeconds + extraSeconds,
                        completedAt = System.currentTimeMillis(),
                        lastResumeTime = 0
                    )

                } else {

                    task.copy(
                        status = "Completed",
                        completedAt = System.currentTimeMillis(),
                        lastResumeTime = 0
                    )
                }

            taskDao.updateTask(updatedTask)
        }
    }

    fun deleteTask(task: TaskEntity) {

        viewModelScope.launch {

            taskDao.deleteTask(task)
        }
    }

    fun getTasksForDate(
        startOfDay: Long,
        endOfDay: Long
    ) = taskDao.getTasksForDate(
        startOfDay,
        endOfDay
    )

    fun getDayStatus(
        tasks: List<TaskEntity>
    ): DayStatus {

        val relevant =
            tasks
                .filter { it.type == "Relevant" }
                .sumOf { it.elapsedSeconds }

        val wasted =
            tasks
                .filter { it.type == "Gap" }
                .sumOf { it.elapsedSeconds }

        val notCore =
            tasks
                .filter { it.type == "Not Core Task" }
                .sumOf { it.elapsedSeconds }

        if (
            relevant == 0L &&
            wasted == 0L &&
            notCore == 0L
        ) {
            return DayStatus.NO_ACTIVITY
        }

        return when {

            relevant > wasted &&
                    relevant > notCore -> DayStatus.GREEN

            wasted > relevant &&
                    wasted > notCore -> DayStatus.RED

            else -> DayStatus.YELLOW
        }
    }

    suspend fun getDayStatusMap(): Map<Long, DayStatus> {

        val tasks = taskDao.getAllCompletedTasks()

        val groupedTasks =
            tasks.groupBy {

                val calendar = Calendar.getInstance().apply {
                    timeInMillis = it.completedAt
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }

                calendar.timeInMillis
            }

        val result = mutableMapOf<Long, DayStatus>()

        groupedTasks.forEach { (date, dayTasks) ->

            val relevant =
                dayTasks
                    .filter { it.type == "Relevant" }
                    .sumOf { it.elapsedSeconds }

            val wasted =
                dayTasks
                    .filter { it.type == "Gap" }
                    .sumOf { it.elapsedSeconds }

            val notCore =
                dayTasks
                    .filter { it.type == "Not Core Task" }
                    .sumOf { it.elapsedSeconds }

            val status = when {

                relevant > wasted &&
                        relevant > notCore -> DayStatus.GREEN

                wasted > relevant &&
                        wasted > notCore -> DayStatus.RED

                else -> DayStatus.YELLOW
            }

            result[date] = status
        }

        return result
    }

    suspend fun getCurrentStreak(): Int {

        val dayStatusMap = getDayStatusMap()

        if (dayStatusMap.isEmpty()) return 0

        val calendar = Calendar.getInstance().apply {

            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

        }

        // 👇 Ye naya code add karo
        val todayStart = calendar.timeInMillis

        // Agar aaj abhi tak koi completed task nahi hai,
        // to calculation kal se shuru hogi
        if (dayStatusMap[todayStart] == null) {

            calendar.add(Calendar.DAY_OF_MONTH, -1)

        }

        var currentDay = calendar.timeInMillis

        var streak = 0

        while (true) {

            when (dayStatusMap[currentDay]) {

                DayStatus.GREEN -> streak++

                else -> break
            }

            calendar.timeInMillis = currentDay
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            currentDay = calendar.timeInMillis
        }

        return streak
    }

    suspend fun getBestStreak(): Int {

        val dayStatusMap = getDayStatusMap()

        if (dayStatusMap.isEmpty()) return 0

        val sortedDays =
            dayStatusMap.keys.sorted()

        var bestStreak = 0
        var currentStreak = 0

        var previousDay: Long? = null

        for (day in sortedDays) {

            val status = dayStatusMap[day]

            if (status == DayStatus.GREEN) {

                if (previousDay == null) {

                    currentStreak = 1

                } else {

                    val difference =
                        (day - previousDay) /
                                (24 * 60 * 60 * 1000)

                    if (difference == 1L) {

                        currentStreak++

                    } else {

                        currentStreak = 1

                    }
                }

                if (currentStreak > bestStreak) {

                    bestStreak = currentStreak

                }

            } else {

                currentStreak = 0

            }

            previousDay = day
        }

        return bestStreak
    }

    suspend fun getSuccessRate(): Int {

        val dayStatusMap = getDayStatusMap()

        val greenDays =
            dayStatusMap.values.count {
                it == DayStatus.GREEN
            }

        val yellowDays =
            dayStatusMap.values.count {
                it == DayStatus.YELLOW
            }

        val redDays =
            dayStatusMap.values.count {
                it == DayStatus.RED
            }

        val totalDays =
            greenDays + yellowDays + redDays

        if (totalDays == 0)
            return 0

        return (
                greenDays * 100
                ) / totalDays
    }

    suspend fun getFocusConsistencyData(): FocusConsistencyData {

        val dayStatusMap = getDayStatusMap()

        val greenDays =
            dayStatusMap.values.count {
                it == DayStatus.GREEN
            }

        val yellowDays =
            dayStatusMap.values.count {
                it == DayStatus.YELLOW
            }

        val redDays =
            dayStatusMap.values.count {
                it == DayStatus.RED
            }

        return FocusConsistencyData(

            currentStreak = getCurrentStreak(),

            bestStreak = getBestStreak(),

            successRate = getSuccessRate(),

            greenDays = greenDays,

            yellowDays = yellowDays,

            redDays = redDays

        )
    }

    suspend fun getBreakStats(): Pair<Long, Int> {

        val tasks = taskDao.getAllCompletedTasks()

        val breakTasks =
            tasks.filter { it.type == "Not Core Task" }

        return Pair(
            breakTasks.sumOf { it.elapsedSeconds },
            breakTasks.size
        )
    }




    // for cloud

    fun uploadToCloud(
        studyId: String
    ) {

        viewModelScope.launch {

            repository.uploadToCloud(studyId)

        }
    }

    fun downloadFromCloud(
        studyId: String
    ) {

        viewModelScope.launch {

            repository.downloadFromCloud(studyId)

        }
    }

}