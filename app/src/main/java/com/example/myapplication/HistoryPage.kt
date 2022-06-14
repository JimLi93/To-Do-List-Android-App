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
import com.example.myapplication.components.addZero
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
        backgroundColor = Rose1
    ) { innerPadding ->
        TaskBody1(
            data = UserData.historytasks,
            modifier = Modifier.padding(innerPadding)
        ) { task ->
            TaskListBar1(
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


@Composable
private fun <T> TaskBody1(
    modifier: Modifier,
    data: List<T>,
    row: @Composable (T) -> Unit
){
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .verticalScroll(rememberScrollState())
        ) {
            data.forEach {
                row(it)
            }
        }
    }
}


@Composable
private fun TaskListBar1(
    modifier: Modifier,
    name: String,
    year: Int,
    month: Int,
    date: Int,
    hour:Int,
    minute: Int,
    id: Int){
    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        color = if(( id % 2 ) == 0) Rose1 else Rose0
    ){
        Column(modifier = modifier){
            Text(text = name, modifier = Modifier.padding(start = 12.dp, top = 8.dp, bottom = 4.dp)
                ,style = MaterialTheme.typography.h4)
            val deadline = "Deadline: "+addZero(year)+"/"+addZero(month)+"/"+
                    addZero(date)+"/"+addZero(hour)+":"+addZero(minute)
            Text(text = deadline, modifier = Modifier.padding(start = 12.dp, bottom = 4.dp)
                ,style = MaterialTheme.typography.h6)
        }
    }
}


