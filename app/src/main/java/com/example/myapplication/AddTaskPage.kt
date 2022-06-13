package com.example.myapplication


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Rose2

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.components.CardButton
import com.example.myapplication.components.SelectDate
import com.example.myapplication.components.SelectItems
import com.example.myapplication.components.SelectTime
import java.util.*

@Composable
fun AddTask(navController: NavHostController) {
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
            AddString("Task name", "")
            //Spacer(Modifier.height(DefaultPadding))
            SelectItems(false, 0,0,0,0,0)
            Spacer(Modifier.height(DefaultPadding))
            AddString("Details", "")
            Spacer(Modifier.height(DefaultPadding))
            /* TODO: store the task before back to "addtask" */
            CardButton(
                navController = navController
                , string = "Add"
                , cardWidth = 100
                , cardHeight = 40
                , onClick = { navController.navigate(BottomBarScreen.TaskList.route) }
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



@Preview
@Composable
fun AddTaskPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        AddTask(navController)
    }
}

private val DefaultPadding = 20.dp
