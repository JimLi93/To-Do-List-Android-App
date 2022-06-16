package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.myapplication.components.CardButton
import com.example.myapplication.components.addZero
import com.example.myapplication.data.Task
import com.example.myapplication.ui.TaskViewModel
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Rose2

// TODO: detail pages should be draggable
@Composable
fun TaskDetail(
    navController: NavHostController,
    viewModel: TaskViewModel,
    selectedTaskIndex: Int
) {
    val task = viewModel.retrieveTask(selectedTaskIndex)
    val data = task.observeAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("DETAILS") },
                modifier = Modifier,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(route = "tasklist") {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                backgroundColor = Rose2
            )
        },
        backgroundColor = Rose1
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val taskName = data.value?.name ?: "Loading..."
            val deadline = "Deadline: " + addZero(data.value?.year ?: 1969) + "/" + addZero(
                data.value?.month ?: 12
            ) + "/" +
                    addZero(data.value?.date ?: 31) + "/" + addZero(
                data.value?.hour ?: 23
            ) + "/" + addZero(data.value?.minute ?: 59)
            val details = "Details:\n" + (data.value?.details ?: "")

            Spacer(Modifier.height(DefaultPadding))
            TaskBarWithEdit(navController, taskName, selectedTaskIndex)
            Text(
                text = deadline,
                modifier = Modifier.padding(top = 24.dp),
                style = MaterialTheme.typography.h5
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 60.dp, end = 60.dp, top = 30.dp),
                text = details,
                style = MaterialTheme.typography.body1
            )
            Spacer(Modifier.height(DefaultPadding * 5))
            CompleteDelete(navController, viewModel, task)

        }


    }

}

@Composable
private fun TaskBarWithEdit(
    navController: NavHostController,
    taskName: String,
    selectedTaskIndex: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        IconButton(
            onClick = { /*no need to be affect*/ },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(iconSize)
        ) {
        }
        Text(text = taskName, style = MaterialTheme.typography.h3)
        IconButton(
            onClick = { navController.navigate("edittask/${selectedTaskIndex}") },
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(iconSize)
        ) {
            Icon(Icons.Filled.Edit, contentDescription = null)
        }
    }
}

@Composable
fun CompleteDelete(
    navController: NavHostController,
    viewModel: TaskViewModel,
    task: LiveData<Task>
) {
    /* TODO: hold the task detail after pressing delete */
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CardButton(navController, string = "Complete", cardWidth = 120, cardHeight = 40) {
            viewModel.completeTask(task.value!!)
            navController.navigate(BottomBarScreen.TaskList.route)
        }

        CardButton(navController, string = "Delete", cardWidth = 120, cardHeight = 40) {
            viewModel.deleteTask(task.value!!)
            navController.navigate(BottomBarScreen.TaskList.route)
        }

    }
}


private val iconSize = 60.dp
private val DefaultPadding = 40.dp
/*
@Preview
@Composable
private fun TaskDetailPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        TaskDetail(navController, 1)
    }
}*/