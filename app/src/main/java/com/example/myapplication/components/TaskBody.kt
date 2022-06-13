package com.example.myapplication.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.Rose0
import com.example.myapplication.ui.theme.Rose1

@Composable
fun <T> TaskBody(
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
fun TaskListBar(
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
