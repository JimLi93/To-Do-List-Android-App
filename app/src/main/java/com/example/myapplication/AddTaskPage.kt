package com.example.myapplication


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Rose2


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
            AddString("Task name")
            //Spacer(Modifier.height(DefaultPadding))
            AddString("Deadline")
            Spacer(Modifier.height(DefaultPadding))
            AddString("Details")
            Spacer(Modifier.height(DefaultPadding * 2))
            Card(shape = RectangleShape, backgroundColor = Rose2,
                /* TODO: store the task before back to "addtask" */
                modifier = Modifier
                    .clickable(onClick = {navController.navigate(BottomBarScreen.TaskList.route)})
            ) {
                Text(text = "Add")
            }
        }
    }

}

@Composable
fun AddString(title: String) {
    var string by rememberSaveable { mutableStateOf("") }
    StatelessAddString(string = string, onStringChange = { string = it }, title)
}

@Composable
fun StatelessAddString(string: String, onStringChange: (String) -> Unit, title: String) {
    Column(
        modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title, modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.h5
        )
        OutlinedTextField(value = string, onValueChange = onStringChange,
            label = { Text(text = title) })
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
