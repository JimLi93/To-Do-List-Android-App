package com.example.myapplication


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.components.NavBar
import com.example.myapplication.components.TaskBody
import com.example.myapplication.components.TaskListBar
import com.example.myapplication.components.TopHeader
import com.example.myapplication.data.HistoryTask
import com.example.myapplication.ui.theme.*


@Composable
fun HistoryPage(navController: NavHostController, historyList: LiveData<List<HistoryTask>>) {
    val historyListState by historyList.observeAsState()
    Scaffold(
        topBar = { TopHeader(
            string = "HISTORY",
            trophy = false,
            backArrow = true,
            index = false,
            navController = navController,
            backArrowDestination = BottomBarScreen.TaskList.route)
        },
        bottomBar = { NavBar(navController) },
        backgroundColor = Rose1
    ) { innerPadding ->
        TaskBody(
            data = historyListState,
            modifier = Modifier.padding(innerPadding)
        ) { task ->
            TaskListBar(
                modifier = Modifier.clickable(
                    onClick = { navController.navigate("taskdetailhistory/${task.id}") }
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

/*
@Preview
@Composable
fun HistoryPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        HistoryPage(navController)
    }
}
 */
