package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.components.CardButton
import com.example.myapplication.components.TopHeader
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.components.addZero
import com.example.myapplication.data.Task

@Composable
fun TaskDetail(
    navController: NavHostController,
    data: Task,
    selectedTaskIndex: Int
) {
    Scaffold(
        topBar = { TopHeader(
            string = "DETAILS",
            trophy = false,
            backArrow = true,
            index = false,
            navController = navController,
            backArrowDestination = BottomBarScreen.TaskList.route)
        },
        backgroundColor = Rose1
    ) { innerPadding ->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally){
            val taskName = data.name
            val deadline = "Deadline: "+addZero(data.year)+"/"+addZero(data.month)+"/"+
                    addZero(data.date)+"/"+addZero(data.hour)+"/"+addZero(data.minute)
            val details = "Details:\n" +data.details

            Spacer(Modifier.height(DefaultPadding))
            TaskBarWithEdit(navController, taskName, selectedTaskIndex)
            Text(text = deadline, modifier = Modifier.padding(top = 24.dp)
                ,style = MaterialTheme.typography.h5)
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp, top = 30.dp)
                , text = details, style = MaterialTheme.typography.body1)
            Spacer(Modifier.height(DefaultPadding*5))
            CompleteDelete(navController)

        }


    }

}

@Composable
private fun TaskBarWithEdit(
    navController: NavHostController,
    taskName: String,
    selectedTaskIndex: Int
){
    Row(modifier = Modifier.fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround) {
        IconButton(
            onClick = { /*no need to be affect*/},
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
fun CompleteDelete(navController: NavHostController){
    /* TODO: hold the task detail after pressing delete */
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly) {
        /* TODO: store the task detail change after pressing complete */
        CardButton(navController, string = "Complete", cardWidth = 120, cardHeight = 40) {
            navController.navigate(BottomBarScreen.TaskList.route)
        }

        CardButton(navController, string = "Delete", cardWidth = 120, cardHeight = 40) {
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