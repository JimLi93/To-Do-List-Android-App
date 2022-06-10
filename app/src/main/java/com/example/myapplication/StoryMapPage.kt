package com.example.myapplication


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.components.DrawMapLine
import com.example.myapplication.data.SolidUserData
import com.example.myapplication.ui.theme.*


@Composable
fun StoryMapPage(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopHeader(
                string = "STORY MAP",
                index = true,
                navController = navController,
            )
        },
        bottomBar = { NavBar(navController) },
        backgroundColor = Rose1

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val currentChapter = 1
            val startIndex = currentChapter * 5 - 5
            val endIndex = currentChapter * 5 - 1
            val data = SolidUserData.stories
            for (i in startIndex..endIndex) {
                Spacing2(
                    chapter = data[i].chapter,
                    subchapter = data[i].subchapter,
                    valid = data[i].valid,
                    navController = navController
                )
            }
            //draw the line in story map
            DrawMapLine(0)
            DrawMapLine(2)
            DrawMapLine(4)
            DrawMapLine(6)
        }
    }
}

@Composable
fun Spacing2(
    chapter: Int,
    subchapter: Int,
    valid: Boolean,
    navController: NavHostController){
    Column(){
        val hh = updp[subchapter - 1]
        Spacer(modifier = Modifier.height(hh.dp))
        Spacing(chapter, subchapter, valid, navController)
    }
}

@Composable
fun Spacing(
    chapter: Int,
    subchapter: Int,
    valid: Boolean,
    navController: NavHostController){
    Row(){
        val hh = leftdp[subchapter - 1]
        Spacer(modifier = Modifier.width(hh.dp))
        StoryButton(chapter, subchapter, valid, navController)
    }
}

@Composable
fun StoryButton(
    chapter: Int,
    subchapter: Int,
    valid: Boolean,
    navController: NavHostController
) {
    val chapterstring = chapter.toString() + "-" + subchapter.toString()
    var showAlertDialog by remember { mutableStateOf(false) }
    val chapterIndex =chapter * 5 + subchapter - 6
    Surface(
        modifier = Modifier
            .clickable(
                onClick = {
                    if (valid) navController.navigate("readstory/${chapterIndex}")
                    else showAlertDialog = true
                }
            )
            .size(100.dp),
        border = BorderStroke(2.dp, Color.Black),
        shape = CircleShape, color = Rose0
    ) {
        if (valid) {
            //Icon(imageVector = Icons.Default.Lock, null)
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = chapterstring,
                    modifier = Modifier.padding(0.dp),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        } else {

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = chapterstring,
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.LightGray
                )
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Lock, null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }

    }
    if(showAlertDialog){
        AlertDialog(
            onDismissRequest = { showAlertDialog = false },
            title = { Text("Oops!")},
            text = {Text("Chapter "+chapter.toString()+"-"+subchapter.toString()+" is unlocked. Please finish deadline to unlock the chapter")},
            confirmButton = {
                Button(onClick = {showAlertDialog = false},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Yellow1)) {
                    Text(text = "OK")
                }
            },

            backgroundColor = Yellow2,
            contentColor = Color.Black,
            shape = RoundedCornerShape(8.dp)
        )
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

private val leftdp: List<Int> = listOf(70, 290, 20 , 170 ,270)
private val updp: List<Int> = listOf(30, 100, 220 , 300 ,480)

