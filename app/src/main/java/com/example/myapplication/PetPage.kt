package com.example.myapplication


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
fun PetPage(navController: NavHostController) {
    Scaffold(
        topBar = { TopHeader("PET") },
        bottomBar = { NavBar(navController) },
        backgroundColor = Rose1
    ) {
    }
}




@Preview(showBackground = true)
@Composable
fun PetPagePreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        PetPage(navController)
    }
}