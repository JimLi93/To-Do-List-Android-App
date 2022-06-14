package com.example.myapplication.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.myapplication.BottomBarScreen
import com.example.myapplication.data.Task
import com.example.myapplication.ui.theme.Rose0
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Rose2
import com.example.myapplication.ui.TaskViewModel
@Composable
fun <T> TaskBody(
    modifier: Modifier,
    data: List<T>?,
    row: @Composable (T) -> Unit
){
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .verticalScroll(rememberScrollState())
        ) {
            data?.forEach {
                row(it)
            }
        }
    }
}


@Composable
fun TaskListBar(
    modifier: Modifier,
    name: String,
    year: Int,
    month: Int,
    date: Int,
    hour:Int,
    minute: Int,
    id: Int,
    navController: NavHostController
    , viewModel: TaskViewModel,
    task : Task
){
    var showAlertDialog by remember { mutableStateOf(false) }
    if(showAlertDialog){
        AlertDialog(
            onDismissRequest = { showAlertDialog = false },
            title = { Text("Congratulation", fontSize = 25.sp)},
            text = {Text("A new story chapter is unlocked.\nDo you want to check it",
                fontSize = 23.sp)
            },
            confirmButton = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically){
                    Button(onClick = { navController.navigate(BottomBarScreen.StoryMap.route +"/${1}") ;
                        viewModel.deleteTask(task)},
                        colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)) {
                        Text(text = "Yes", fontSize = 20.sp)
                    }
                    Button(onClick = {navController.navigate(BottomBarScreen.TaskList.route)},
                        colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)) {
                        Text(text = "No", fontSize = 20.sp)
                    }
                }
            },

            backgroundColor = Rose1,
            contentColor = Color.Black,
            shape = RoundedCornerShape(8.dp)
        )
    }
    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        color = if(( id % 2 ) == 0) Rose1 else Rose0
    ){

        Row(modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween){
            Column(modifier = modifier) {
                Text(
                    text = name,
                    modifier = Modifier.padding(start = 12.dp, top = 8.dp, bottom = 4.dp),
                    style = MaterialTheme.typography.h4
                )
                val deadline = "Deadline: " + addZero(year) + "/" + addZero(month) + "/" +
                        addZero(date) + "/" + addZero(hour) + ":" + addZero(minute)
                Text(
                    text = deadline,
                    modifier = Modifier.padding(start = 12.dp, bottom = 4.dp),
                    style = MaterialTheme.typography.h6
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Spacer(modifier = Modifier.width(12.dp))
            CardButton(navController, string = "Complete", cardWidth = 120, cardHeight = 40,
                onClick = {showAlertDialog = true})
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}
