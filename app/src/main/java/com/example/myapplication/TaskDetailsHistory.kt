package com.example.myapplication


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.components.CardButton
import com.example.myapplication.components.TopHeader
import com.example.myapplication.components.addZero
import com.example.myapplication.data.UserData
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose1

@Composable
fun TaskDetailHistory(navController: NavHostController, selectedTaskIndex: Int) {
    Scaffold(
        topBar = { TopHeader(
            string = "DETAILS",
            trophy = false,
            backArrow = true,
            index = false,
            navController = navController,
            backArrowDestination = "historypage")
        },
        backgroundColor = Rose1
    ) {
        // innerPadding ->
        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            val data = UserData.historyTasks[selectedTaskIndex]
            val deadline = "Deadline: "+addZero(data.year)+"/"+addZero(data.month)+"/"+
                    addZero(data.date)+"/"+addZero(data.hour)+"/"+addZero(data.minute)
            val details = "Details:\n" +data.details

            Spacer(Modifier.height(DefaultPadding))
            Text(text = data.name, fontSize = 45.sp, style = MaterialTheme.typography.h3)
            Text(text = deadline, modifier = Modifier.padding(top = 24.dp)
                , style = MaterialTheme.typography.h5)
            Text(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp, top = 30.dp)
                , text = details, style = MaterialTheme.typography.body1)
            Spacer(Modifier.height(DefaultPadding * 5))
            CardButton(navController, string = "Delete", cardWidth = 120, cardHeight = 40) {
                navController.navigate("historypage")
            }
        }


    }

}



private val iconSize = 60.dp
private val DefaultPadding = 40.dp

@Preview
@Composable
private fun TaskDetailHistoryPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        TaskDetailHistory(navController, 1)
    }
}