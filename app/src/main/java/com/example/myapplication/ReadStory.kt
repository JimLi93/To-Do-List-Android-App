package com.example.myapplication

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
    val headerString = "Chapter " + data.chapter.toString() + "-" + data.subchapter.toString()
    val story = data.detail
    Scaffold(
        topBar = {
            TopHeader(
                string = headerString,
                backArrow = true,
                navController = navController,
                backArrowDestination = "iii"
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
            story.forEach { it ->
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
    if (id == 0) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = content, modifier = Modifier.padding(textPadding + 4.dp)
                , style = MaterialTheme.typography.body2)
        }
    } else if (id == 1) {
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
    } else {
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
                    modifier = Modifier.height(characterHeight),
                    alpha = 1.5f
                )
                Text(text = character, style = MaterialTheme.typography.subtitle1)
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
