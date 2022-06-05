package com.example.myapplication.data

import android.accounts.Account
import androidx.compose.ui.graphics.Color


data class Task(
    val name: String,
    val year: Int,
    val month: Int,
    val date: Int,
    val details: String
)


object UserData {
    val tasks: List<Task> = listOf(
        Task(
            "Task1",
            2022,
            5,
            30,
            "126377864789326647893"
        ),
        Task(
            "Task1",
            2022,
            5,
            30,
            "126377864789326647893"
        ),
        Task(
            "Task1",
            2022,
            5,
            30,
            "126377864789326647893"
        ),
        Task(
            "Task1",
            2022,
            5,
            30,
            "126377864789326647893"
        ),
        Task(
            "Task1",
            2022,
            5,
            30,
            "126377864789326647893"
        ),
        Task(
            "Task1",
            2022,
            5,
            30,
            "126377864789326647893"
        ),
        Task(
            "Task1",
            2022,
            5,
            30,
            "126377864789326647893"
        ),
        Task(
            "Task1",
            2022,
            5,
            30,
            "126377864789326647893"
        )
    )

/*
    fun getAccount(accountName: String?): Account {
        return tasks.first { it.name == accountName }
    }*/
}