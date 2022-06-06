package com.example.myapplication


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.IconSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.Task
import com.example.myapplication.data.UserData
import com.example.myapplication.ui.theme.*


@Composable
fun AddTask(navController: NavHostController) {
    Scaffold(
        topBar = { TopHeader("ADD TASK", false, true, false) },
        backgroundColor = Rose1
    ) {innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally) {
            AddString("Task name")
            //Spacer(Modifier.height(DefaultPadding))
            AddString("Deadline")
            Spacer(Modifier.height(DefaultPadding))
            AddString("Details")
            Spacer(Modifier.height(DefaultPadding*2))
            Card(shape = RectangleShape, backgroundColor = Rose2,
                modifier = Modifier
                    .clickable { /*TODO*/ }) {
                Text(text = "Add")
            }
        }
    }

}

@Composable
fun AddString(title: String){
    var string by rememberSaveable{ mutableStateOf("")}
    StatelessAddString(string = string, onStringChange = { string = it }, title)
}

@Composable
fun StatelessAddString(string: String, onStringChange: (String) -> Unit, title: String){
    Column(modifier = Modifier.padding(12.dp)
        , horizontalAlignment = Alignment.CenterHorizontally){
        Text(text = title, modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.h5)
        OutlinedTextField(value = string, onValueChange = onStringChange,
        label = {Text(text = title)})
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
