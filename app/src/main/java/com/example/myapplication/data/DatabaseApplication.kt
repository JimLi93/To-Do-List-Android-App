package com.example.myapplication.data

import android.app.Application

class DatabaseApplication : Application() {
    val database: TaskRoomDatabase by lazy { TaskRoomDatabase.getDatabase(this) }
}