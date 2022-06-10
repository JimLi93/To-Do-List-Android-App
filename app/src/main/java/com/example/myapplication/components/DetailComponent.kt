package com.example.myapplication.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.theme.Rose2

@Composable
fun CardButton(navController: NavHostController, string: String,
                cardWidth: Int, cardHeight: Int, onClick: () -> Unit){
    Card(shape = RoundedCornerShape(20.dp), backgroundColor = Rose2,
        modifier = Modifier
            .size(width = cardWidth.dp, height = cardHeight.dp)
            .clickable(onClick = onClick )
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Text(text = string, style = MaterialTheme.typography.h5,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold)
        }
    }
}