package com.example.myapplication.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * from task_table")
    fun getListOfTasks(): Flow<List<Task>>

    @Query("SELECT * from task_table WHERE id = :id")
    fun getTask(id: Int): Flow<Task>

    @Insert(entity = Task::class)
    suspend fun insert(task: Task)

    @Update(entity = Task::class)
    suspend fun update(task: Task)

    @Delete(entity = Task::class)
    suspend fun delete(task: Task)

    @Query("SELECT * from history_table")
    fun getListOfHistory(): Flow<List<HistoryTask>>

    @Query("SELECT * from history_table WHERE id = :id")
    fun getHistoryTask(id: Int): Flow<HistoryTask>

    @Insert(entity = HistoryTask::class)
    suspend fun insertHistory(historyTask: HistoryTask)

    @Delete(entity = HistoryTask::class)
    suspend fun deleteHistory(historyTask: HistoryTask)
}