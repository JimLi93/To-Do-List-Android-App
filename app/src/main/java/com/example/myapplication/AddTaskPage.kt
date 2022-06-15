package com.example.myapplication


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.theme.Rose1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import com.example.myapplication.components.CardButton
import com.example.myapplication.components.SelectItems
import com.example.myapplication.components.TopHeader
import com.example.myapplication.ui.TaskViewModel

@Composable
fun AddTask(navController: NavHostController, viewModel: TaskViewModel) {
    Scaffold(
        topBar = {
            TopHeader(
                string = "ADD TASK",
                trophy = false,
                backArrow = true,
                index = false,
                navController = navController,
                backArrowDestination = BottomBarScreen.TaskList.route,
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
            var taskName by rememberSaveable { mutableStateOf("") }
            // AddString("Task name", "")
            StatelessAddString(
                string = taskName,
                onStringChange = { taskName = it },
                title = "Task name"
            )
            //Spacer(Modifier.height(DefaultPadding))
            SelectItems(false, 0,0,0,0,0)
            Spacer(Modifier.height(DefaultPadding))
            var taskDetail by rememberSaveable { mutableStateOf("") }
            // AddString("Details", "")
            StatelessAddString(
                string = taskDetail,
                onStringChange = { taskDetail = it },
                title = "Details"
            )
            Spacer(Modifier.height(DefaultPadding))
            /* TODO: store the task before back to "addtask" */
            CardButton(
                navController = navController
                , string = "Add"
                , cardWidth = 100
                , cardHeight = 40
                , onClick = {
                    // TODO: retrieve date and time
                    viewModel.addTask(taskName, 2022, 6, 14, 15, 30, taskDetail)
                    navController.navigate(BottomBarScreen.TaskList.route)
                }
            )
        }
    }

}

@Composable
fun AddString(title: String, dataString: String) {
    var string by rememberSaveable { mutableStateOf(dataString) }
    StatelessAddString(string = string, onStringChange = { string = it }, title)
}

@Composable
fun StatelessAddString(string: String, onStringChange: (String) -> Unit, title: String) {
    Column(
        modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title, modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.body1
        )
        OutlinedTextField(value = string, onValueChange = onStringChange
            , textStyle = MaterialTheme.typography.h5
            ,label = { Text(text = title , style = MaterialTheme.typography.h5) })
    }
}


/*
@Preview
@Composable
fun AddTaskPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        AddTask(navController)
    }
}
 */
private val DefaultPadding = 20.dp
