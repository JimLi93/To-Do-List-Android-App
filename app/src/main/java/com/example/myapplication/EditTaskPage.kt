package com.example.myapplication


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
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
            AddString(
                string = taskName,
                onStringChange = { taskName = it },
                title = "Task name"
            )
            var mYear by rememberSaveable { mutableStateOf(data.year) }
            var mMonth by rememberSaveable { mutableStateOf(data.month) }
            var mDate by rememberSaveable { mutableStateOf(data.date) }
            var mHour by rememberSaveable { mutableStateOf(data.hour) }
            var mMinute by rememberSaveable { mutableStateOf(data.minute) }
            SelectItems(true, mYear, mMonth, mDate, mHour, mMinute,
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
            var taskDetail by rememberSaveable { mutableStateOf(data.details) }
            AddString(
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
                    viewModel.updateTask(taskName, mYear, mMonth, mDate, mHour, mMinute,
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
