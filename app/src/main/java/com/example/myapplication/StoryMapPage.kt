package com.example.myapplication


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose1
import com.example.myapplication.ui.theme.Rose2


@Composable
fun StoryMapPage(navController: NavHostController) {
    Scaffold(
        topBar = { TopHeader("STORY MAP", false, false, true) },
        bottomBar = { NavBar(navController) },
        backgroundColor = Rose1

    ) {
    }
}



private val iconSize = 60.dp

@Preview
@Composable
fun StoryMapPagePreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        StoryMapPage(navController)
    }
}
