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
import com.example.myapplication.data.Task
import com.example.myapplication.data.UserData
import com.example.myapplication.ui.theme.*


@Composable
fun HistoryPage(navController: NavHostController) {
    Scaffold(
        topBar = { TopHeader("HISTORY", false, true, false) },
        bottomBar = { NavBar(navController) },
        backgroundColor = Rose3
    ) {
            innerPadding ->
        HistoryPageBody(
            data = UserData.historytasks,
            modifier = Modifier.padding(innerPadding)
        ) { task ->
            HistoryListBar(
                modifier = Modifier.clickable { },
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

@Composable
fun <T> HistoryPageBody(
    modifier: Modifier,
    data: List<T>,
    row: @Composable (T) -> Unit
){
    Box(modifier = modifier){
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
fun HistoryListBar(
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
        Column(){
            Text(text = name, modifier = Modifier.padding(start = 12.dp, top = 8.dp, bottom = 4.dp)
                ,style = MaterialTheme.typography.h4)
            val deadline = "Deadline: "+addZero(year)+"/"+addZero(month)+"/"+
                    addZero(date)+"/"+addZero(hour)+"/"+addZero(minute)
            Text(text = deadline, modifier = Modifier.padding(start = 12.dp, bottom = 4.dp)
                ,style = MaterialTheme.typography.subtitle2)
        }
    }
}

private fun addZero(num: Int): String {
    if( num < 10 ) {
        val ans = "0" + num.toString()
        return ans
    }
    else return num.toString()
}





@Preview
@Composable
fun HistoryPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        HistoryPage(navController)
    }
}

