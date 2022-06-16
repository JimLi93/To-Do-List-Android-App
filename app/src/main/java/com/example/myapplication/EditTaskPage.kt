package com.example.myapplication


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.myapplication.components.CardButton
import com.example.myapplication.components.SelectItems
import com.example.myapplication.components.TopHeader
import com.example.myapplication.ui.TaskViewModel
import com.example.myapplication.ui.theme.Rose2

@Composable
fun EditTask(
    navController: NavHostController,
    viewModel: TaskViewModel,
    selectedTaskIndex: Int
) {
    val data = viewModel.retrieveBufferedTaskForEdit()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "EDIT TASK", textAlign = TextAlign.End) },
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
            var taskName by rememberSaveable { mutableStateOf(data.name) }
            // AddString("Task name", data.name)
            StatelessAddString(
                string = taskName,
                onStringChange = { taskName = it },
                title = "Task name"
            )
            SelectItems(true, data.year, data.month, data.date, data.hour, data.minute)
            Spacer(Modifier.height(DefaultPadding))
            var taskDetail by rememberSaveable { mutableStateOf(data.details) }
            // AddString("Details", data.details)
            StatelessAddString(
                string = taskDetail,
                onStringChange = { taskDetail = it },
                title = "Details"
            )
            Spacer(Modifier.height(DefaultPadding))
            CardButton(
                navController = navController
                , string = "Save"
                , cardWidth = 100
                , cardHeight = 40
                , onClick = {
                    viewModel.updateTask(taskName, 2022, 6, 14, 15, 30,
                        taskDetail, selectedTaskIndex)
                    navController.navigate(BottomBarScreen.TaskList.route)
                }
            )
        }
    }

}
/*
@Preview
@Composable
fun EditTaskPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        EditTask(navController, 0)
    }
}
 */
private val DefaultPadding = 20.dp
