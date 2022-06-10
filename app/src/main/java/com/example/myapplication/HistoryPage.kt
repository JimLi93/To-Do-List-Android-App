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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.components.TaskBody
import com.example.myapplication.components.TaskListBar
import com.example.myapplication.data.Task
import com.example.myapplication.data.UserData
import com.example.myapplication.ui.theme.*


@Composable
fun HistoryPage(navController: NavHostController) {
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
        backgroundColor = Rose3
    ) { innerPadding ->
        TaskBody(
            data = UserData.historytasks,
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


@Preview
@Composable
fun HistoryPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        HistoryPage(navController)
    }
}

