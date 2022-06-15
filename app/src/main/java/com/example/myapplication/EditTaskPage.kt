package com.example.myapplication


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose1

import androidx.compose.runtime.Composable
import com.example.myapplication.components.CardButton
import com.example.myapplication.components.SelectItems
import com.example.myapplication.components.TopHeader
import com.example.myapplication.data.UserData

@Composable
fun EditTask(
    navController: NavHostController,
    selectedTaskIndex: Int
) {
    Scaffold(
        topBar = {
            TopHeader(
                string = "EDIT TASK",
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
            val data = UserData.tasks[selectedTaskIndex]
            AddString("Task name", data.name)
            SelectItems(true, data.year, data.month, data.date, data.hour, data.minute)
            Spacer(Modifier.height(DefaultPadding))
            AddString("Details", data.details)
            Spacer(Modifier.height(DefaultPadding))
            /* TODO: store the task before back to "edittask" */
            CardButton(
                navController = navController
                , string = "Save"
                , cardWidth = 100
                , cardHeight = 40
                , onClick = { navController.navigate(BottomBarScreen.TaskList.route) }
            )
        }
    }

}

@Preview
@Composable
fun EditTaskPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        EditTask(navController, 0)
    }
}

private val DefaultPadding = 20.dp
