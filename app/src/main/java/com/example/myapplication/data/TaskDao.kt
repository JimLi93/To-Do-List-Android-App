package com.example.myapplication.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * from task_table")
    fun getListOfTasks(): Flow<List<Task>>

    @Query("SELECT * from task_table WHERE id = :id")
    fun getTask(id: Long): Flow<Task>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}