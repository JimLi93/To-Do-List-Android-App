package com.example.myapplication


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.IconSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.components.TaskBody
import com.example.myapplication.components.TaskListBar
import com.example.myapplication.data.Task
import com.example.myapplication.data.UserData
import com.example.myapplication.ui.TaskViewModel
import com.example.myapplication.ui.theme.*


@Composable
fun TaskPage(navController: NavHostController, taskList: LiveData<List<Task>>, viewModel: TaskViewModel) {
    val taskListState by taskList.observeAsState()
    Scaffold(
        topBar = { TopHeader(
                    string = "TASK",
                    trophy = true,
                    backArrow = false,
                    index = false,
                    navController = navController,
                    trophyDestination = "historypage")
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
                    onClick = { }
                ),
                name = task.name,
                year = task.year,
                month = task.month,
                date = task.date,
                hour = task.hour,
                minute = task.minute,
                id = task.id,
                navController,
                viewModel = viewModel,
                task = task
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

