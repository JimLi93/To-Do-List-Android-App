package com.example.myapplication

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.data.UserData
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Rose2
import com.example.myapplication.ui.theme.Rose3

@Composable
fun TaskDetail(navController: NavHostController, selectedTaskIndex: Int) {
    Scaffold(
        topBar = { TopHeader("DETAILS", false, true, false) },
        backgroundColor = Rose1
    ) {
            innerPadding ->
        Column (modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(Modifier.height(DefaultPadding))
            val data = UserData.tasks[selectedTaskIndex]
            Row(modifier = Modifier.fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                IconButton(
                    onClick = { /*no need to be affect*/},
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(iconSize)
                ) {
                    //Icon(Icons.Filled.Edit, contentDescription = null)
                }
                Text(text = data.name, style = MaterialTheme.typography.h4)
                IconButton(
                    onClick = { /*TODO*/},
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(iconSize)
                ) {
                    Icon(Icons.Filled.Edit, contentDescription = null)
                }
            }
            val deadline = "Deadline: "+addZero(data.year)+"/"+addZero(data.month)+"/"+
                    addZero(data.date)+"/"+addZero(data.hour)+"/"+addZero(data.minute)
            Text(text = deadline, modifier = Modifier.padding(start = 12.dp, bottom = 4.dp)
                ,style = MaterialTheme.typography.subtitle2)
            Spacer(Modifier.height(DefaultPadding))
            val details = "Details:\n" +data.details
            Text(modifier = Modifier.padding(start = 60.dp, end = 60.dp).fillMaxWidth()
                , text = details, style = MaterialTheme.typography.h5)
            Spacer(Modifier.height(DefaultPadding*10))
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround) {
                Card(shape = RectangleShape, backgroundColor = Rose2,
                    modifier = Modifier
                        .clickable { /*TODO*/ }) {
                    Text(text = "Complete")
                }
                Card(shape = RectangleShape, backgroundColor = Rose2,
                    modifier = Modifier
                        .clickable { /*TODO*/ }) {
                    Text(text = "Delete")
                }
            }
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

private val iconSize = 60.dp
private val DefaultPadding = 20.dp