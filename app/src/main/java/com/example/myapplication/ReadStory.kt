package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.SolidUserData
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose1

@Composable
fun ReadStory(
    chapterIndex: Int,
    navController: NavHostController,
) {
    val data = SolidUserData.stories[chapterIndex]
    val headerString = "Chapter" + data.chapter.toString() + "-" + data.subchapter.toString()
    val story = data.detail
    Scaffold(
        topBar = {
            TopHeader(
                string = headerString,
                backArrow = true,
                navController = navController,
                backArrowDestination = "storymappage"
            )
        },
        backgroundColor = Rose1
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            story.forEach { it ->
                ReadingBar(
                    it.id,
                    it.character,
                    it.characterImage,
                    it.content
                )
            }
            Text(text = "- To be continued -")
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
    if (id == 0) {
        Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = content)
        }
    } else if (id == 1) {
        Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
                Text(text = character)
            }
            Text(text = content)
        }
    } else {
        Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
            Text(text = content)
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
                Text(text = character)
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