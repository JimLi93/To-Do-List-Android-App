package com.example.myapplication


import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.UserData
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Rose2
import com.example.myapplication.ui.theme.Rose3

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
            innerPadding ->
        Column (modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            val data = UserData.historytasks[selectedTaskIndex]
            val deadline = "Deadline: "+addZero(data.year)+"/"+addZero(data.month)+"/"+
                    addZero(data.date)+"/"+addZero(data.hour)+"/"+addZero(data.minute)
            val details = "Details:\n" +data.details

            Spacer(Modifier.height(DefaultPadding))
            Text(text = data.name, fontSize = 45.sp, style = MaterialTheme.typography.h4
                , fontWeight = FontWeight.Bold)
            Text(text = deadline, modifier = Modifier.padding(top = 24.dp)
                , style = MaterialTheme.typography.subtitle1)
            Text(modifier = Modifier.fillMaxWidth()
                .padding(start = 60.dp, end = 60.dp, top = 30.dp)
                , text = details, style = MaterialTheme.typography.h5
                , fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(DefaultPadding * 5))
            CompleteDelete(navController)
        }


    }

}

@Composable
private fun CompleteDelete(navController: NavHostController){
    /* TODO: hold the task detail after pressing delete */
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
        Card(shape = RoundedCornerShape(20.dp), backgroundColor = Rose2,
            modifier = Modifier
                .size(width = cardWidth, height = cardHeight)
                .clickable(onClick = { navController.navigate("historypage") })
        ) {
            CardContext(string = "Delete")
        }
    }
}

@Composable
private fun CardContext(string: String){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center){
        Text(text = string, style = MaterialTheme.typography.h5,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold)
    }
}

private fun addZero(num: Int): String {
    if( num < 10 ) {
        val ans = "0" + num.toString()
        return ans
    }
    else return num.toString()
}

private val iconSize = 60.dp
private val DefaultPadding = 40.dp
private val cardWidth = 120.dp
private val cardHeight = 40.dp

@Preview
@Composable
private fun TaskDetailHistoryPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        TaskDetailHistory(navController, 1)
    }
}