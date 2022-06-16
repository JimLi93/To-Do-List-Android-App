package com.example.myapplication


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.theme.Rose1

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.myapplication.components.CardButton
import com.example.myapplication.components.SelectItems
import com.example.myapplication.ui.TaskViewModel
import com.example.myapplication.ui.theme.Rose2

@Composable
fun AddTask(navController: NavHostController, viewModel: TaskViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ADD TASK") },
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
            var taskName by rememberSaveable { mutableStateOf("") }
            AddString(
                string = taskName,
                onStringChange = { taskName = it },
                title = "Task name"
            )
            var mYear by rememberSaveable { mutableStateOf(0) }
            var mMonth by rememberSaveable { mutableStateOf(0) }
            var mDate by rememberSaveable { mutableStateOf(0) }
            var mHour by rememberSaveable { mutableStateOf(0) }
            var mMinute by rememberSaveable { mutableStateOf(0) }
            SelectItems(false, 0,0,0,0,0,
                { newYear, newMonth, newDate ->
                    mYear = newYear
                    mMonth = newMonth + 1
                    mDate = newDate
                }, { newHour, newMinute ->
                    mHour = newHour
                    mMinute = newMinute
                }
            )
            Spacer(Modifier.height(DefaultPadding))
            var taskDetail by rememberSaveable { mutableStateOf("") }
            AddString(
                string = taskDetail,
                onStringChange = { taskDetail = it },
                title = "Details"
            )
            Spacer(Modifier.height(DefaultPadding))
            CardButton(
                navController = navController
                , string = "Add"
                , cardWidth = 100
                , cardHeight = 40
                , onClick = {
                    viewModel.addTask(taskName, mYear, mMonth, mDate, mHour, mMinute, taskDetail)
                    navController.navigate(BottomBarScreen.TaskList.route)
                }
            )
        }
    }

}

@Composable
fun AddString(string: String, onStringChange: (String) -> Unit, title: String) {
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
