package com.example.myapplication

import android.view.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Greeting(navController: NavHostController) {
    Surface (modifier = Modifier.clickable(onClick = { navController.navigate("petpage")}) ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val fontFamily = FontFamily(
                Font(R.font.oregano, FontWeight.ExtraBold)
            )
            Text(text = "23:59", modifier = Modifier.padding(top = 30.dp)
                , color = Color.Black, fontSize = 70.sp, fontFamily = fontFamily)
            Image(painter = painterResource(R.drawable.greeting_cat), contentDescription = null
                , modifier = Modifier.width(300.dp) .height(300.dp))
            Text(text = "Welcome", color = Color.Black, fontSize = 70.sp, fontFamily = fontFamily)
            Text(text = "back", color = Color.Black, fontSize = 70.sp, fontFamily = fontFamily)
            Text(text = "-Tap to Continue-", modifier = Modifier.padding(top = 10.dp), color = Color.Black, fontSize = 20.sp, fontFamily = fontFamily)
        }
    }
}