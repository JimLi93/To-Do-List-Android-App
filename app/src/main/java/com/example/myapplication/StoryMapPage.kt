package com.example.myapplication


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Sort
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.SolidUserData
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose0
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Rose2


@Composable
fun StoryMapPage(navController: NavHostController) {
    Scaffold(
        topBar = { TopHeader("STORY MAP", false, false, true) },
        bottomBar = { NavBar(navController) },
        backgroundColor = Rose1

    ) {
        innerPadding->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()) {
            val currentChapter = 1
            val startIndex = currentChapter * 5 - 5
            val endIndex = currentChapter * 5 - 1
            val data = SolidUserData.stories
            Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround){
                for(i in startIndex..endIndex){
                    StoryButton(
                        chapter = data[i].chapter,
                        subchapter = data[i].subchapter,
                        valid = data[i].valid)
                }
            }
        }
    }
}

@Composable
fun StoryButton(
    chapter: Int,
    subchapter: Int,
    valid: Boolean){
    Surface(modifier = Modifier.clickable { /*TODO*/ }.size(100.dp),
        border = BorderStroke(2.dp, Color.Black),
        shape = CircleShape, color = Rose0) {
        val chapterstring = chapter.toString() + "-" + subchapter.toString()
        if(valid == true) {
            //Icon(imageVector = Icons.Default.Lock, null)
            Row(modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
                , verticalAlignment = Alignment.CenterVertically){
                Text(text = chapterstring, modifier = Modifier.padding(0.dp)
                    ,style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold)
            }
        }
        else {

            Row(modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
                , verticalAlignment = Alignment.CenterVertically){
                Text(text = chapterstring, modifier = Modifier.padding(12.dp)
                    ,style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold,
                    color = Color.LightGray)
            }
            Row(modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
                , verticalAlignment = Alignment.CenterVertically){
                Icon(imageVector = Icons.Default.Lock, null,
                    modifier = Modifier.size(40.dp))
            }
        }

    }
}


@Preview
@Composable
fun StoryMapPagePreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        StoryMapPage(navController)
    }
}
