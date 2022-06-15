package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
data class Task(
    val name: String,
    val year: Int,
    val month: Int,
    val date: Int,
    val hour:Int,
    val minute: Int,
    val details: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)


object UserData {
    val tasks: List<Task> = listOf(
        Task(
            "Task1",
            2022,
            5,
            30,
            17,
            0,
            "126377864789326647893",
            0
        ),
        Task(
            "Task2",
            2022,
            8,
            30,
            17,
            0,
            "126377864789326647893",
            1
        ),
        Task(
            "Task3",
            2022,
            10,
            30,
            17,
            0,
            "126377864789326647893",
            2
        ),
        Task(
            "Task4",
            2022,
            12,
            30,
            17,
            0,
            "126377864789326647893",
            3
        ),
        Task(
            "Task5",
            2022,
            5,
            1,
            17,
            0,
            "126377864789326647893",
            4
        ),
        Task(
            "Task6",
            2022,
            5,
            30,
            17,
            0,
            "126377864789326647893",
            5
        ),
        Task(
            "Task7",
            2022,
            5,
            30,
            17,
            0,
            "126377864789326647893",
            6
        ),
        Task(
            "Task8",
            2022,
            5,
            30,
            17,
            0,
            "126377864789326647893",
            7
        )

    )
    val historyTasks: List<Task> = listOf(
        Task(
            "Task1",
            2022,
            5,
            30,
            17,
            0,
            "126377864789326647893",
            0
        ),
        Task(
            "Task2",
            2022,
            8,
            30,
            17,
            0,
            "126377864789326647893",
            1
        ),
        Task(
            "Task3",
            2022,
            10,
            30,
            17,
            0,
            "126377864789326647893",
            2
        ),
        Task(
            "Task4",
            2022,
            12,
            30,
            17,
            0,
            "126377864789326647893",
            3
        ),
        Task(
            "Task5",
            2022,
            5,
            1,
            17,
            0,
            "126377864789326647893",
            4
        ),
        Task(
            "Task6",
            2022,
            5,
            30,
            17,
            0,
            "126377864789326647893",
            5
        ),
        Task(
            "Task7",
            2022,
            5,
            30,
            17,
            0,
            "126377864789326647893",
            6
        ),
        Task(
            "Task8",
            2022,
            5,
            30,
            17,
            0,
            "126377864789326647893",
            7
        )
    )

/*
    fun getAccount(accountName: String?): Account {
        return tasks.first { it.name == accountName }
    }*/
}