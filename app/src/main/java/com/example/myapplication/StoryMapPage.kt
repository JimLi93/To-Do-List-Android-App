package com.example.myapplication


import android.service.autofill.OnClickAction
import android.view.MenuItem
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.components.DrawMapLine
import com.example.myapplication.data.SolidUserData
import com.example.myapplication.ui.theme.*
import kotlinx.coroutines.launch




@Composable
fun StoryMapPage(navController: NavHostController, chapterIndex: Int = 1, congratulation: Int) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            StoryTopHeader(
                string = "STORY MAP",
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerBody(navController)
        },
        drawerShape = customShape(),
        drawerBackgroundColor = Rose1,
        bottomBar = { NavBar(navController) },
        backgroundColor = Rose1

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            var showAlertDialog by remember { mutableStateOf(congratulation == 2) }
            if(showAlertDialog){
                AlertDialog(
                    onDismissRequest = { showAlertDialog = false },
                    title = { Text("Congratulation!!!", fontSize = 30.sp)},
                    text = {Text("Chapter 1-3 is unlocked.\nHave a good time reading.",
                        fontSize = 23.sp)
                    },
                    confirmButton = {
                        Button(onClick = {showAlertDialog = false},
                            colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)) {
                            Text(text = "OK", fontSize = 20.sp)
                        }
                    },

                    backgroundColor = Rose1,
                    contentColor = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                )
            }
            val startIndex = chapterIndex * 5 - 5
            val endIndex = chapterIndex * 5 - 1
            val data = SolidUserData.stories

            for (i in startIndex..endIndex) {
                Spacing2(
                    chapter = data[i].chapter,
                    subchapter = data[i].subchapter,
                    valid = if(i == 2 && congratulation != 0) true else data[i].valid,
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
            title = { Text("Oops!", fontSize = 25.sp)},
            text = {Text("Chapter "+chapter.toString()+"-"+subchapter.toString()+" is locked. Please finish deadline to unlock the chapter.",
                 fontSize = 23.sp)
                   },
            confirmButton = {
                Button(onClick = {showAlertDialog = false},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)) {
                    Text(text = "OK", fontSize = 20.sp)
                }
            },

            backgroundColor = Rose1,
            contentColor = Color.Black,
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Composable
fun DrawerBody(navController: NavHostController){
    Column(modifier = Modifier
        .width(indexWidth)
        .height(indexCircleSize * 5 + 25.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(Modifier.height(spacerHeight))
        for(i in 1..5){
            Surface (modifier = Modifier
                .size(indexCircleSize)
                .padding(circlePadding)
                , shape = CircleShape, color = Rose0){
                DrawerCircle(i, navController)
            }
            Spacer(Modifier.height(spacerHeight))
        }
    }
}

@Composable
fun DrawerCircle(i: Int, navController: NavHostController){
    //TODO: wrong i should be 5*i - 5
    var showAlertDialog by remember { mutableStateOf(false) }
    val data = SolidUserData.stories[i-1]
    val valid = data.valid
    if(showAlertDialog){
        AlertDialog(
            onDismissRequest = { showAlertDialog = false },
            title = { Text("Oops!", fontSize = 25.sp)},
            text = {Text("Chapter "+i.toString()+" is locked. Please finish deadline to unlock the chapter.",
                fontSize = 23.sp)
            },
            confirmButton = {
                Button(onClick = {showAlertDialog = false},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Rose2)) {
                    Text(text = "OK", fontSize = 20.sp)
                }
            },

            backgroundColor = Rose1,
            contentColor = Color.Black,
            shape = RoundedCornerShape(8.dp)
        )
    }
    if (valid) {
        //Icon(imageVector = Icons.Default.Lock, null)
        Row(
            modifier = Modifier
                .clickable(
                    onClick = { navController.navigate(route = "storyMap/${i}") }
                )
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = i.toString(),
                modifier = Modifier.padding(0.dp),
                style = MaterialTheme.typography.body1,
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
                text = i.toString(),
                modifier = Modifier.padding(0.dp),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.ExtraBold,
                color = Color.LightGray
            )
        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = { showAlertDialog = true }),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Lock, null,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}


@Composable
fun StoryTopHeader(string :String, onClick: () -> Unit) {
    Surface(color = Rose2){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(iconSize)
            ) {
                Icon(Icons.Filled.List, contentDescription = null)
            }
            Text(
                text = string,
                modifier = Modifier.align(Alignment.CenterVertically),
                style = MaterialTheme.typography.h2
            )
            IconButton(
                enabled = false,
                onClick = { },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(iconSize)
            ) {
                //Icon(Icons.Filled.Sort, contentDescription = null)
            }
        }
    }
}
/*
@Preview
@Composable
fun StoryMapPagePreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        StoryMapPage(navController, 1)
    }
}
*/

private val leftdp: List<Int> = listOf(70, 290, 20 , 170 ,270)
private val updp: List<Int> = listOf(30, 100, 220 , 300 ,480)
private val iconSize = 60.dp

fun customShape() = object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rounded(RoundRect(left = 0f, top = 0f, right = size.width / 5, bottom = size.height*38/100,
            bottomRightCornerRadius = CornerRadius(40f,40f))
            )
    }


}

private val indexWidth = 70.dp
private val indexCircleSize = 50.dp
private val circlePadding = 4.dp
private val spacerHeight = 5.dp
