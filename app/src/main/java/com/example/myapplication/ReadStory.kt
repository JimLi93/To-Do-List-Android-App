package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.components.TopHeader
import com.example.myapplication.data.SolidUserData
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Rose2

@Composable
fun ReadStory(
    chapterIndex: Int,
    navController: NavHostController,
) {
    val data = SolidUserData.stories[chapterIndex]
    val headerString = "Chapter " + data.chapter.toString() + "-" + data.subchapter.toString()
    val story = data.detail
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = headerString) },
                modifier = Modifier,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(route = "storymappage/${data.chapter}") {
                                popUpTo(navController.graph.findStartDestination().id)
                                launchSingleTop = true
                            }
                        },
                    ) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                backgroundColor = Rose2
            )
        },
        backgroundColor = Rose1
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            story.forEach {
                ReadingBar(
                    it.id,
                    it.character,
                    it.characterImage,
                    it.content
                )
            }
            Column (modifier = Modifier.fillMaxWidth()
                , horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "- To be continued -", modifier = Modifier.padding(30.dp),
                style = MaterialTheme.typography.body1)
            }

        }
    }
}


@Composable
fun ReadingBar(
    id: Int,
    character: String,
    image: Int,
    content: String
) {
    when (id) {
        0 -> {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = content, modifier = Modifier.padding(textPadding + 4.dp),
                    style = MaterialTheme.typography.body2)
            }
        }
        1 -> {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(spacerdp))
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        modifier = Modifier.height(characterHeight)
                    )
                    Text(text = character, style = MaterialTheme.typography.subtitle1)
                }
                Text(
                    text = content, modifier = Modifier
                        .padding(start = textPadding, end = textPadding, top = textPadding / 3)
                        .fillMaxWidth()
                        .height(characterHeight),
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.body2
                )
            }
        }
        else -> {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.width(spacerdp))
                Text(text = content, modifier = Modifier
                    .padding(start = textPadding, end = textPadding, top = textPadding / 3)
                    .width(300.dp),
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.body2)
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = null,
                        modifier = Modifier.height(characterHeight)
                    )
                    Text(text = character, style = MaterialTheme.typography.subtitle1)
                }
            }
        }
    }
}

@Preview
@Composable
private fun ReadStoryPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        ReadStory(chapterIndex = 5, navController = navController)
    }
}

private val textPadding = 12.dp
private val spacerdp = 12.dp
private val characterHeight = 80.dp
