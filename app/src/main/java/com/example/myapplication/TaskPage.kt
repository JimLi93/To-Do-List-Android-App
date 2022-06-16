package com.example.myapplication


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.myapplication.components.NavBar
import com.example.myapplication.components.TaskBody
import com.example.myapplication.components.TaskListBar
import com.example.myapplication.components.TopHeader
import com.example.myapplication.data.Task
import com.example.myapplication.ui.theme.*


@Composable
fun TaskPage(navController: NavHostController, taskList: LiveData<List<Task>>) {
    val taskListState by taskList.observeAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TASKPAGE") },
                modifier = Modifier,
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(route = "historypage") {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                    ) {
                        Icon(Icons.Filled.HistoryEdu, contentDescription = null)
                    }
                },
                backgroundColor = Rose2
            )
        },
        bottomBar = { NavBar(navController) },
        backgroundColor = Rose1,
        floatingActionButton = { AddTaskButton(navController) }
    ) {
        innerPadding ->
        TaskBody(
            data = taskListState,
            modifier = Modifier.padding(innerPadding),
        ) { task ->
            TaskListBar(
                modifier = Modifier.clickable(
                    onClick = { navController.navigate(route = "taskdetail/${task.id}") }
                ),
                name = task.name,
                year = task.year,
                month = task.month,
                date = task.date,
                hour = task.hour,
                minute = task.minute,
                id = task.id
            )
        }
    }

}



@Composable
fun AddTaskButton(navController: NavHostController){
    FloatingActionButton(
        onClick = { navController.navigate("addtask") },
        modifier = Modifier.padding(0.dp),
        backgroundColor = Rose3
    ) {
        Icon(imageVector = Icons.Default.Add, null)
    }
}

// TODO: fake data base
/*
@Preview
@Composable
fun TaskPagePreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        TaskPage(navController)
    }
}*/

