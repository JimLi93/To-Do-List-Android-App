package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.Rose1


@Composable
fun Greeting(navController: NavHostController) {
    Surface(
        modifier = Modifier.clickable(
            onClick = { navController.navigate(BottomBarScreen.TaskList.route) }
        ),
        color = Rose1
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val fontFamily = FontFamily(
                Font(R.font.oregano, FontWeight.ExtraBold)
            )
            // TODO: Access real time
            Text(
                text = "23:59",
                modifier = Modifier.padding(top = 30.dp),
                color = Color.Black,
                style = MaterialTheme.typography.h1
            )
            Image(
                painter = painterResource(R.drawable.scp682),
                contentDescription = null,
                modifier = Modifier
                    .width(300.dp)
                    .height(300.dp)
                    .padding(30.dp)
            )
            Text(text = "Welcome", color = Color.Black, style = MaterialTheme.typography.h1)
            Text(text = "back", color = Color.Black, style = MaterialTheme.typography.h1)
            Text(
                text = "-Tap to Continue-",
                modifier = Modifier.padding(top = 10.dp),
                color = Color.Black,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        Greeting(navController)
    }
}