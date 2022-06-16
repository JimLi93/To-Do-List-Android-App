package com.example.myapplication.ui

import androidx.lifecycle.*
import com.example.myapplication.data.Task
import com.example.myapplication.data.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(private val taskDao: TaskDao) : ViewModel() {

    val allTasks: LiveData<List<Task>> = taskDao.getListOfTasks().asLiveData()

    lateinit var currentTaskCache: LiveData<Task>
    private var currentTaskId: Int? = null
    fun retrieveTask(id: Int): LiveData<Task> {
        if (currentTaskId == null || id != currentTaskId) {
            currentTaskCache = taskDao.getTask(id).asLiveData()
            currentTaskId = id
        }
        return currentTaskCache
    }
    fun retrieveBufferedTaskForEdit(): Task {
        return currentTaskCache.value!!
    }

    fun addTask(
        name: String,
        year: Int,
        month: Int,
        date: Int,
        hour: Int,
        minute: Int,
        details: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.insert(Task(name, year, month, date, hour, minute, details))
        }
    }

    fun updateTask(
        name: String,
        year: Int,
        month: Int,
        date: Int,
        hour: Int,
        minute: Int,
        details: String,
        id: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.update(Task(name, year, month, date, hour, minute, details, id))
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.delete(task)
        }
    }
}

class TaskViewModelFactory(private val taskDao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
